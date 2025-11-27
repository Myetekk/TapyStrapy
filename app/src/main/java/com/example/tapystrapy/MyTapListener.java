package com.example.tapystrapy;

import android.util.Log;
import com.tapwithus.sdk.TapListener;
import com.tapwithus.sdk.TapSdk;
import com.tapwithus.sdk.airmouse.AirMousePacket;
import com.tapwithus.sdk.mode.Point3;
import com.tapwithus.sdk.mode.RawSensorData;
import com.tapwithus.sdk.mouse.MousePacket;
import org.jetbrains.annotations.NotNull;

public class MyTapListener implements TapListener {
    private final TapSdk tapSdk;
    private final MainActivity mainActivity;
    private int gyroThresholdInt = 20000;

    public MyTapListener(TapSdk tapSdk, MainActivity mainActivity) {
        this.tapSdk = tapSdk;
        this.mainActivity = mainActivity;
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
        mainActivity.updateConnectionStatus(true);
        mainActivity.setTapId(tapIdentifier);
        mainActivity.onConnected();

//        int[] vibrationPattern = {500, 100, 500, 100, 500};
//        this.tapSdk.vibrate(tapIdentifier, vibrationPattern);
    }

    @Override
    public void onTapDisconnected(@NotNull String tapIdentifier) {
        Log.d("TAP", "TAP device " + tapIdentifier + " disconnected");
        mainActivity.updateConnectionStatus(false);
        mainActivity.onDisconnected();
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
    public void onTapInputReceived(@NotNull String tapIdentifier, int data, int repeatData) {
        boolean[] fingers = TapSdk.toFingers(data);

//        Log.d("TAP", "Tap received: " + data);
//        Log.d("TAP", "Thumb: " + fingers[0]);
//        Log.d("TAP", "Index: " + fingers[1]);
//        Log.d("TAP", "Middle: " + fingers[2]);
//        Log.d("TAP", "Ring: " + fingers[3]);
//        Log.d("TAP", "Pinky: " + fingers[4]);
//        Log.d("TAP", "Repeat count: " + repeatData);

        mainActivity.updateFingerStatus(fingers, data, repeatData);
        mainActivity.updateMode(new boolean[] {true, false, false, false});
        Log.d("TAPPP", "onTapInputReceived | " + String.valueOf(data));
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
//        mainActivity.updateMode(new boolean[] {false, false, false, true});
//        Log.d("TAPPP", "onMouseInputReceived | " + String.valueOf(data));
    }

    @Override
    public void onAirMouseInputReceived(@NotNull String tapIdentifier, @NotNull AirMousePacket data) {
        tapSdk.startRawSensorMode(tapIdentifier, (byte)0, (byte)0, (byte)0);
        mainActivity.updateMode(new boolean[] {false, true, false, false});
        Log.d("TAPPP", "onAirMouseInputReceived | " + String.valueOf(data));
    }

    @Override
    public void onRawSensorInputReceived(@NotNull String tapIdentifier, RawSensorData rsData) {
        // realnie podaje tylko żyro - dane dla 'thumb' są identyczne jak żyro
        // Receives raw sensor data from accelerometers and IMU

        Point3 gyro = rsData.getPoint(RawSensorData.iIMU_GYRO);
        if (gyro != null && (Math.abs(gyro.x)>gyroThresholdInt || Math.abs(gyro.y) >gyroThresholdInt || Math.abs(gyro.z)>gyroThresholdInt)) {
            Log.d("TAP", "Gyro - X: " + gyro.x + ", Y: " + gyro.y + ", Z: " + gyro.z);
            mainActivity.updateGyro(new double[]{gyro.x, gyro.y, gyro.z});
        }
    }

    @Override
    public void onTapChangedState(@NotNull String tapIdentifier, int state) {
        // TAP device state changed
        // 0- tap, 1- air mouse, 3- mouse
        boolean[] modes = new boolean[4];
        if (state == 0) modes = new boolean[] {true, false, false, false};
        if (state == 1) modes = new boolean[] {false, true, false, false};
        mainActivity.updateMode(modes);
    }

    @Override
    public void onError(@NotNull String tapIdentifier, int code, @NotNull String description) {
        // Handle errors
        Log.e("TAP", "Error for device " + tapIdentifier + ": " + description);
    }
}
