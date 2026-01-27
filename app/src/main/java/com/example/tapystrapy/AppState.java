package com.example.tapystrapy;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;
import com.tapwithus.sdk.TapSdk;
import com.example.tapystrapy.model.Gesture;
import com.tapwithus.sdk.TapSdkFactory;

public class AppState {
    private static AppState instance;
    private Activity currentActivity;

    private ActivitiesActivity activitiesActivity;
    private ConfirmationActivity confirmationActivity;
    private DebugActivity debugActivity;
    private EmotionLevel emotionLevel;
    private FeelingsActivity feelingsActivity;
    private FinalActivity finalActivity;
    private MainActivity mainActivity;
    private PainHandSelection painHandSelection;
    private PainHeadSelection painHeadSelection;
    private PainLeftRightSelection painLeftRightSelection;
    private PainLegSelection painLegSelection;
    private PainMainSelection painMainSelection;
    private PainTorsoSelection painTorsoSelection;


    private TapSdk tapSdk;
    private MyTapListener tapListener;
    private String tapIdentifier;
    private boolean connectionStatus;
    private Gesture gesture;

    private AppState() {}

    public static synchronized AppState getInstance() {
        if (instance == null) {
            instance = new AppState();
        }
        return instance;
    }


    public Activity get_activity() { return currentActivity; }
    public void set_activity(Activity activity) {
        this.currentActivity = activity;
        Log.d("TAPPP", "currentActivity: " + currentActivity.getClass().getSimpleName());
        switch(currentActivity.getClass().getSimpleName()) {
            case "ActivitiesActivity": activitiesActivity = (ActivitiesActivity) activity; break;
            case "ConfirmationActivity": confirmationActivity = (ConfirmationActivity) activity; break;
            case "DebugActivity": debugActivity = (DebugActivity) activity; break;
            case "EmotionLevel": emotionLevel = (EmotionLevel) activity; break;
            case "FeelingsActivity": feelingsActivity = (FeelingsActivity) activity; break;
            case "FinalActivity": finalActivity = (FinalActivity) activity; break;
            case "MainActivity": mainActivity = (MainActivity) activity; break;
            case "PainHandSelection": painHandSelection = (PainHandSelection) activity; break;
            case "PainHeadSelection": painHeadSelection = (PainHeadSelection) activity; break;
            case "PainLeftRightSelection": painLeftRightSelection = (PainLeftRightSelection) activity; break;
            case "PainLegSelection": painLegSelection = (PainLegSelection) activity; break;
            case "PainMainSelection": painMainSelection = (PainMainSelection) activity; break;
            case "PainTorsoSelection": painTorsoSelection = (PainTorsoSelection) activity; break;
        }
    }



    // MyTapListener
    public MyTapListener get_tapListener() { return tapListener; }
    public void set_tapSdk(TapSdk tapSdk) { this.tapSdk = tapSdk; }
    public void set_tapListener(MyTapListener tapListener) { this.tapListener = tapListener; }
    public void initializeTapSdk() {
        try {
            if (mainActivity != null) {
                tapSdk = TapSdkFactory.getDefault(mainActivity);
                if (tapSdk != null) {
                    tapListener = new MyTapListener(tapSdk);
                    tapSdk.registerTapListener(tapListener);
                } else {
                    Toast.makeText(mainActivity, "Failed to initialize Tap SDK", Toast.LENGTH_SHORT).show();
                    Log.e("TAP", "Failed to initialize Tap SDK ");
                }
            }
        } catch (Exception e) {
            Toast.makeText(mainActivity, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
            Log.e("TAP", "Error: " + e.getMessage());
        }
    }
    public void destroyTapSdk() {
        try {
            tapSdk.unregisterTapListener(tapListener);
            tapSdk = null;
            tapListener = null;
        } catch (Exception e) {
            Toast.makeText(mainActivity, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
            Log.e("TAP", "Error: " + e.getMessage());
        }
    }
    public boolean get_connectionStatus() { return connectionStatus; }
    public void set_connectionStatus(boolean connectionStatus) { this.connectionStatus = connectionStatus; }
    public void set_tapIdentifier(String tapIdentifier) { this.tapIdentifier = tapIdentifier; }
    public void call_startControllerMode() { if (tapSdk != null) tapSdk.startControllerMode(tapIdentifier); }
    public void call_startRawSensorMode() { if (tapSdk != null) tapSdk.startRawSensorMode(tapIdentifier, (byte)0, (byte)0, (byte)0); }
    // gesture
    public Gesture get_gesture() { return gesture; }
    public void set_gesture(Gesture gesture) {
        this.gesture = gesture;

        switch(currentActivity.getClass().getSimpleName()) {
            case "FeelingsActivity": feelingsActivity.changeChosenElement(gesture); break;
            case "MainActivity": mainActivity.changeChosenElement(gesture); break;
        }
    }



    // DebugActivity
    public void call_updateFingerStatus(boolean[] fingers, int fingersId, int repeatData) { if (debugActivity != null) debugActivity.updateFingerStatus(fingers, fingersId, repeatData); }
    public void call_updateGyro(double[] gyro) { if (debugActivity != null) debugActivity.updateGyro(gyro); }
    public void call_updateConnectionStatus() { if (debugActivity != null) debugActivity.updateConnectionStatus(connectionStatus); }
    public void call_onConnected() {
        if (debugActivity != null) debugActivity.onConnected();
        if (feelingsActivity != null) feelingsActivity.changeChosenElement(Gesture.NONE);
        if (mainActivity != null) mainActivity.changeChosenElement(Gesture.NONE);
    }
    public void call_onDisconnected() { if (debugActivity != null) debugActivity.onDisconnected(); }
    public void call_updateMode(boolean[] modes) { if (debugActivity != null) debugActivity.updateMode(modes); }


}
