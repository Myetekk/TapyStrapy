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
    private final DebugActivity debugActivity;
    private int gyroThresholdInt = 50000;

    public MyTapListener(TapSdk tapSdk, DebugActivity debugActivity) {
        this.tapSdk = tapSdk;
        this.debugActivity = debugActivity;
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
        debugActivity.updateConnectionStatus(true);
        debugActivity.setTapId(tapIdentifier);
        debugActivity.onConnected();
    }

    @Override
    public void onTapDisconnected(@NotNull String tapIdentifier) {
        Log.d("TAP", "TAP device " + tapIdentifier + " disconnected");
        debugActivity.updateConnectionStatus(false);
        debugActivity.onDisconnected();
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

        debugActivity.updateFingerStatus(fingers, data, repeatData);
        debugActivity.updateMode(new boolean[] {true, false, false, false});
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
//        debugActivity.updateMode(new boolean[] {false, false, false, true});
//        Log.d("TAPPP", "onMouseInputReceived | " + String.valueOf(data));
    }

    @Override
    public void onAirMouseInputReceived(@NotNull String tapIdentifier, @NotNull AirMousePacket data) {
        tapSdk.startRawSensorMode(tapIdentifier, (byte)0, (byte)0, (byte)0);
        debugActivity.updateMode(new boolean[] {false, true, false, false});
        Log.d("TAPPP", "onAirMouseInputReceived | " + String.valueOf(data));
    }

    @Override
    public void onRawSensorInputReceived(@NotNull String tapIdentifier, RawSensorData rsData) {
        // Receives raw sensor data from accelerometers and IMU

        Point3 gyro = rsData.getPoint(RawSensorData.iIMU_GYRO);
        if (gyro != null && (Math.abs(gyro.x)>gyroThresholdInt || Math.abs(gyro.y) >gyroThresholdInt || Math.abs(gyro.z)>gyroThresholdInt)) {
            Log.d("TAP", "Gyro - X: " + gyro.x + ", Y: " + gyro.y + ", Z: " + gyro.z);
            debugActivity.updateGyro(new double[]{gyro.x, gyro.y, gyro.z});
        }
    }

    @Override
    public void onTapChangedState(@NotNull String tapIdentifier, int state) {
        // TAP device state changed
        // 0- tap, 1- air mouse, 3- mouse
        boolean[] modes = new boolean[4];
        if (state == 0) modes = new boolean[] {true, false, false, false};
        if (state == 1) modes = new boolean[] {false, true, false, false};
        debugActivity.updateMode(modes);
    }

    @Override
    public void onError(@NotNull String tapIdentifier, int code, @NotNull String description) {
        // Handle errors
        Log.e("TAP", "Error for device " + tapIdentifier + ": " + description);
    }
}
