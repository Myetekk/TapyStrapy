package com.example.tapystrapy;

import android.util.Log;
import com.tapwithus.sdk.TapListener;
import com.tapwithus.sdk.TapSdk;
import com.tapwithus.sdk.airmouse.AirMousePacket;
import com.tapwithus.sdk.mode.Point3;
import com.tapwithus.sdk.mode.RawSensorData;
import com.tapwithus.sdk.mouse.MousePacket;

public class MyTapListener implements TapListener {
    private final TapSdk tapSdk;
    private int gyroThresholdInt = 50000;
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
    public void onTapStartConnecting(String tapIdentifier) {
        // TAP device is connecting
        Log.d("TAP", "TAP device " + tapIdentifier + " is connecting");
    }

    @Override
    public void onTapConnected(String tapIdentifier) {
        Log.d("TAP", "TAP connected: " + tapIdentifier);

        int[] vibrationPattern = {500, 100, 500, 100, 500};
        this.tapSdk.vibrate(tapIdentifier, vibrationPattern);
    }

    @Override
    public void onTapDisconnected(String tapIdentifier) {
        // TAP device disconnected
        Log.d("TAP", "TAP device " + tapIdentifier + " disconnected");
    }

    @Override
    public void onTapResumed(String tapIdentifier) {
        // TAP device resumed (returned from background)
    }

    @Override
    public void onTapChanged(String tapIdentifier) {
        // TAP device changed
    }

    @Override
    public void onTapInputReceived(String tapIdentifier, int data, int repeatData) {
        // Most important callback - receives tap input data
        // data: 8-bit unsigned integer (1-31) representing finger combination
        // repeatData: 1 for single tap, 2 for double, 3 for triple

        boolean[] fingers = com.tapwithus.sdk.TapSdk.toFingers(data);

        Log.d("TAP", "Tap received: " + data);
        Log.d("TAP", "Thumb: " + fingers[0]);
        Log.d("TAP", "Index: " + fingers[1]);
        Log.d("TAP", "Middle: " + fingers[2]);
        Log.d("TAP", "Ring: " + fingers[3]);
        Log.d("TAP", "Pinky: " + fingers[4]);
        Log.d("TAP", "Repeat count: " + repeatData);
    }

    @Override
    public void onTapShiftSwitchReceived(String tapIdentifier, int data) {
        // Receives shift and switch states
        int[] shiftSwitch = com.tapwithus.sdk.TapSdk.toShiftAndSwitch(data);
        int shiftState = shiftSwitch[0]; // 0=off, 1=on, 2=locked
        int switchState = shiftSwitch[1]; // 0=off, !0=on

        Log.d("TAP", "Shift state: " + shiftState + ", Switch state: " + switchState);
    }

    @Override
    public void onMouseInputReceived(String tapIdentifier, MousePacket data) {
        // Receives mouse input if in Controller with Mouse HID mode
    }

    @Override
    public void onAirMouseInputReceived(String tapIdentifier, AirMousePacket data) {
        tapSdk.startRawSensorMode(tapIdentifier, (byte)0, (byte)0, (byte)0);
    }

    @Override
    public void onRawSensorInputReceived(String tapIdentifier, RawSensorData rsData) {
        // Receives raw sensor data from accelerometers and IMU
        if (rsData.dataType == RawSensorData.DataType.Device) {
            // Finger accelerometer data
            Point3 thumb = rsData.getPoint(RawSensorData.iDEV_THUMB);
            if (thumb != null) {
                Log.d("TAP", "Thumb accel - X: " + thumb.x + ", Y: " + thumb.y + ", Z: " + thumb.z);
            }
        } else if (rsData.dataType == RawSensorData.DataType.IMU) {
            // IMU data from thumb sensor
            Point3 gyro = rsData.getPoint(RawSensorData.iIMU_GYRO);
            if (gyro != null && (Math.abs(gyro.x)>gyroThresholdInt || Math.abs(gyro.y) >gyroThresholdInt || Math.abs(gyro.z)>gyroThresholdInt)) {
                Log.d("TAP", "Gyro - X: " + gyro.x + ", Y: " + gyro.y + ", Z: " + gyro.z);
            }
        }
    }

    @Override
    public void onTapChangedState(String tapIdentifier, int state) {
        // TAP device state changed
    }

    @Override
    public void onError(String tapIdentifier, int code, String description) {
        // Handle errors
        Log.e("TAP", "Error for device " + tapIdentifier + ": " + description);
    }
}
