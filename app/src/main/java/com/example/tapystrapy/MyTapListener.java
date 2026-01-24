package com.example.tapystrapy;

import android.os.Handler;
import android.os.Looper;
import com.example.tapystrapy.model.Gesture;
import android.util.Log;
import com.tapwithus.sdk.TapListener;
import com.tapwithus.sdk.TapSdk;
import com.tapwithus.sdk.airmouse.AirMousePacket;
import com.tapwithus.sdk.mode.Point3;
import com.tapwithus.sdk.mode.RawSensorData;
import com.tapwithus.sdk.mouse.MousePacket;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MyTapListener implements TapListener {
    private final TapSdk tapSdk;
    private final int gyroThresholdInt = 60000;
    private static final int inactivityTimeout = 400;
    private ArrayList<Gesture> gestureList = new ArrayList<Gesture>();

    public MyTapListener(TapSdk tapSdk) {
        this.tapSdk = tapSdk;
    }

    @Override
    public void onBluetoothTurnedOn() {
        // Handle Bluetooth turned on
    }

    @Override
    public void onBluetoothTurnedOff() {
        // Handle Bluetooth turned off
    }

    @Override
    public void onTapStartConnecting(@NotNull String tapIdentifier) {
        // TAP device is connecting
        Log.d("TAP", "TAP device " + tapIdentifier + " is connecting");
    }

    @Override
    public void onTapConnected(@NotNull String tapIdentifier) {
        Log.d("TAP", "TAP connected: " + tapIdentifier);
        AppState.getInstance().set_connectionStatus(true);
        AppState.getInstance().call_updateConnectionStatus();
        AppState.getInstance().set_tapIdentifier(tapIdentifier);
        AppState.getInstance().call_onConnected();
    }

    @Override
    public void onTapDisconnected(@NotNull String tapIdentifier) {
        Log.d("TAP", "TAP device " + tapIdentifier + " disconnected");
        AppState.getInstance().set_connectionStatus(false);
        AppState.getInstance().call_updateConnectionStatus();
        AppState.getInstance().call_onDisconnected();

        inactivityHandler.removeCallbacks(resetRunnable);
    }

    @Override
    public void onTapResumed(@NotNull String tapIdentifier) {
        // TAP device resumed (returned from background)
    }

    @Override
    public void onTapChanged(@NotNull String tapIdentifier) {
        // TAP device changed
    }

    @Override
    public void onTapInputReceived(@NotNull String tapIdentifier, int fingersId, int repeatData) {
        boolean[] fingers = TapSdk.toFingers(fingersId);

        AppState.getInstance().call_updateFingerStatus(fingers, fingersId, repeatData);
        AppState.getInstance().call_updateMode(new boolean[]{true, false, false, false});

        Log.d("TAPPP", "onTapInputReceived | " + String.valueOf(fingersId));
    }

    @Override
    public void onTapShiftSwitchReceived(@NotNull String tapIdentifier, int data) {
        // Receives shift and switch states
        int[] shiftSwitch = TapSdk.toShiftAndSwitch(data);
        int shiftState = shiftSwitch[0]; // 0=off, 1=on, 2=locked
        int switchState = shiftSwitch[1]; // 0=off, !0=on

        Log.d("TAP", "Shift state: " + shiftState + ", Switch state: " + switchState);
    }

    @Override
    public void onMouseInputReceived(@NotNull String tapIdentifier, @NotNull MousePacket data) {
        // Receives mouse input if in Controller with Mouse HID mode
//        tapSdk.startControllerWithMouseHIDMode(tapIdentifier);
//        AppState.getInstance().call_updateMode(new boolean[]{false, false, false, true});
//        Log.d("TAPPP", "onMouseInputReceived | " + String.valueOf(data));
    }

    @Override
    public void onAirMouseInputReceived(@NotNull String tapIdentifier, @NotNull AirMousePacket data) {
        tapSdk.startRawSensorMode(tapIdentifier, (byte)0, (byte)0, (byte)0);
        AppState.getInstance().call_updateMode(new boolean[]{false, true, false, false});
        Log.d("TAPPP", "onAirMouseInputReceived | " + String.valueOf(data));
    }

    @Override
    public void onRawSensorInputReceived(@NotNull String tapIdentifier, RawSensorData rsData) {
        // Receives raw sensor data from accelerometers and IMU

        Point3 gyro = rsData.getPoint(RawSensorData.iIMU_GYRO);
        if (gyro != null && (Math.abs(gyro.x)>gyroThresholdInt*0.75 || Math.abs(gyro.y)>gyroThresholdInt*0.7 || Math.abs(gyro.z)>gyroThresholdInt*0.75)) {
            Log.d("TAP", "Gyro - X: " + gyro.x + ", Y: " + gyro.y + ", Z: " + gyro.z);
            AppState.getInstance().call_updateGyro(new double[]{gyro.x, gyro.y, gyro.z});

            identifySingleGesture(gyro);

            inactivityHandler.removeCallbacks(resetRunnable);
            inactivityHandler.postDelayed(resetRunnable, inactivityTimeout);
        }
    }

    @Override
    public void onTapChangedState(@NotNull String tapIdentifier, int state) {
        // TAP device state changed
        // 0- tap, 1- air mouse, 3- mouse
        boolean[] modes = new boolean[4];
        if (state == 0) modes = new boolean[] {true, false, false, false};
        if (state == 1) modes = new boolean[] {false, true, false, false};

        AppState.getInstance().call_updateMode(new boolean[]{false, true, false, false});
    }

    @Override
    public void onError(@NotNull String tapIdentifier, int code, @NotNull String description) {
        // Handle errors
        Log.e("TAP", "Error for device " + tapIdentifier + ": " + description);
    }



    private final Handler inactivityHandler = new Handler(Looper.getMainLooper());
    private final Runnable resetRunnable = () -> {
        Gesture gesture = identifyGesture();
        AppState.getInstance().set_gesture(gesture);

        Log.d("TAP_gesture", String.valueOf(gestureList));
        Log.d("TAP_gesture", "GESTURE: " + gesture);
        Log.d("TAP", "GESTURE: " + gesture);
        Log.d("TAPPP", "GESTURE: " + gesture);
        Log.d("TAP_gesture", "");

        gestureList.clear();
    };

    private void identifySingleGesture(Point3 gyro) {
        if (gyro.y < -gyroThresholdInt*0.75  &&  gyro.y < gyro.z*2.5) {
            Log.d("TAP_gesture", "UP      Gyro - X: " + gyro.x + ", Y: " + gyro.y + ", Z: " + gyro.z);
            gestureList.add(Gesture.UP);
        }
        else if (gyro.z < -gyroThresholdInt  &&  gyro.z < gyro.y*1.05) {
            Log.d("TAP_gesture", "RIGHT   Gyro - X: " + gyro.x + ", Y: " + gyro.y + ", Z: " + gyro.z);
            gestureList.add(Gesture.RIGHT);
        }
        else if (gyro.z > gyroThresholdInt  &&  gyro.z > gyro.y*1.1) {
            Log.d("TAP_gesture", "LEFT    Gyro - X: " + gyro.x + ", Y: " + gyro.y + ", Z: " + gyro.z);
            gestureList.add(Gesture.LEFT);
        } else {
            Log.d("TAP_gesture", "        Gyro - X: " + gyro.x + ", Y: " + gyro.y + ", Z: " + gyro.z);
        }
    }

    private Gesture identifyGesture() {
        if (gestureList.size() == 1) { return gestureList.get(0); }
        else if (gestureList.size() > 1) {
            Gesture firstGesture, mostCommonGesture = Gesture.NONE;
            int countUp = 0, countRight = 0, countLeft = 0;

            firstGesture = gestureList.get(0);
            Log.d("TAP_gesture", "firstGesture:      " + firstGesture);

            for (int i=0; i<gestureList.size()/2; i++) {
                switch (gestureList.get(i)) {
                    case UP: countUp++; break;
                    case RIGHT: countRight++; break;
                    case LEFT: countLeft++; break;
                }
            }
            if (countUp == countRight  &&  countUp == countLeft) { mostCommonGesture = Gesture.NONE; }
            else if (countUp == countRight  &&  (firstGesture == Gesture.UP || firstGesture == Gesture.RIGHT)) { mostCommonGesture = firstGesture; }
            else if (countUp == countLeft  &&  (firstGesture == Gesture.UP || firstGesture == Gesture.RIGHT)) { mostCommonGesture = firstGesture; }
            else if (countRight == countLeft  &&  (firstGesture == Gesture.UP || firstGesture == Gesture.RIGHT)) { mostCommonGesture = firstGesture; }
            else if (countUp > countRight  &&  countUp > countLeft) { mostCommonGesture = Gesture.UP; }
            else if (countRight > countUp  &&  countRight > countLeft) { mostCommonGesture = Gesture.RIGHT; }
            else if (countLeft > countUp  &&  countLeft > countRight) { mostCommonGesture = Gesture.LEFT; }
            Log.d("TAP_gesture", "mostCommonGesture: " + mostCommonGesture);

            return mostCommonGesture;
        }

        return Gesture.NONE;
    }
}
