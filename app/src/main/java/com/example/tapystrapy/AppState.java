package com.example.tapystrapy;

import com.tapwithus.sdk.TapSdk;

public class AppState {
    private static AppState instance;
    private TapSdk tapSdk;
    private MyTapListener tapListener;
    private String tapIdentifier;
    private boolean connectionStatus;
    private DebugActivity debugActivity;

    private AppState() {}

    public static synchronized AppState getInstance() {
        if (instance == null) {
            instance = new AppState();
        }
        return instance;
    }


    // MyTapListener
    public MyTapListener get_tapListener() { return tapListener; }
    public void set_tapSdk(TapSdk tapSdk) { this.tapSdk = tapSdk; }
    public void set_tapListener(MyTapListener tapListener) { this.tapListener = tapListener; }
    public void set_connectionStatus(boolean connectionStatus) { this.connectionStatus = connectionStatus; }
    public void set_tapIdentifier(String tapIdentifier) { this.tapIdentifier = tapIdentifier; }
    public void call_startControllerMode() { if (tapSdk != null) tapSdk.startControllerMode(tapIdentifier); }
    public void call_startRawSensorMode() { if (tapSdk != null) tapSdk.startRawSensorMode(tapIdentifier, (byte)0, (byte)0, (byte)0); }


    // DebugActivity
    public DebugActivity get_debugActivity() { return debugActivity; }
    public void set_debugActivity(DebugActivity activity) { this.debugActivity = activity; }
    public void call_updateFingerStatus(boolean[] fingers, int fingersId, int repeatData) { if (debugActivity != null) debugActivity.updateFingerStatus(fingers, fingersId, repeatData); }
    public void call_updateGyro(double[] gyro) { if (debugActivity != null) debugActivity.updateGyro(gyro); }
    public void call_updateConnectionStatus() { if (debugActivity != null) debugActivity.updateConnectionStatus(connectionStatus); }
    public void call_onConnected() { if (debugActivity != null) debugActivity.onConnected(); }
    public void call_onDisconnected() { if (debugActivity != null) debugActivity.onDisconnected(); }
    public void call_updateMode(boolean[] modes) { if (debugActivity != null) debugActivity.updateMode(modes); }


}
