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

    private AskAddressSelection askAddressSelection;
    private AskBasicneedsSelection askBasicneedsSelection;
    private AskItemsSelection askItemsSelection;
    private AskLocalisationsSelection askLocalisationsSelection;
    private AskMainSelection askMainSelection;
    private AskPersonsSelection askPersonsSelection;
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
        switch(currentActivity.getClass().getSimpleName()) {
            case "AskAddressSelection": askAddressSelection = (AskAddressSelection) activity; break;
            case "AskBasicneedsSelection": askBasicneedsSelection = (AskBasicneedsSelection) activity; break;
            case "AskItemsSelection": askItemsSelection = (AskItemsSelection) activity; break;
            case "AskLocalisationsSelection": askLocalisationsSelection = (AskLocalisationsSelection) activity; break;
            case "AskMainSelection": askMainSelection = (AskMainSelection) activity; break;
            case "AskPersonsSelection": askPersonsSelection = (AskPersonsSelection) activity; break;
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
            case "AskAddressSelection": askAddressSelection.changeChosenElement(gesture); break;
            case "AskBasicneedsSelection": askBasicneedsSelection.changeChosenElement(gesture); break;
            case "AskItemsSelection": askItemsSelection.changeChosenElement(gesture); break;
            case "AskLocalisationsSelection": askLocalisationsSelection.changeChosenElement(gesture); break;
            case "AskMainSelection": askMainSelection.changeChosenElement(gesture); break;
            case "AskPersonsSelection": askPersonsSelection.changeChosenElement(gesture); break;
            case "ConfirmationActivity": confirmationActivity.changeChosenElement(gesture); break;
            case "EmotionLevel": emotionLevel.changeChosenElement(gesture); break;
            case "FeelingsActivity": feelingsActivity.changeChosenElement(gesture); break;
            case "FinalActivity": finalActivity.changeChosenElement(gesture); break;
            case "MainActivity": mainActivity.changeChosenElement(gesture); break;
            case "PainHeadSelection": painHeadSelection.changeChosenElement(gesture); break;
            case "PainHandSelection": painHandSelection.changeChosenElement(gesture); break;
            case "PainLeftRightSelection": painLeftRightSelection.changeChosenElement(gesture); break;
            case "PainLegSelection": painLegSelection.changeChosenElement(gesture); break;
            case "PainMainSelection": painMainSelection.changeChosenElement(gesture); break;
            case "PainTorsoSelection": painTorsoSelection.changeChosenElement(gesture); break;
        }
    }



    // DebugActivity
    public void call_updateFingerStatus(boolean[] fingers, int fingersId, int repeatData) { if (debugActivity != null) debugActivity.updateFingerStatus(fingers, fingersId, repeatData); }
    public void call_updateGyro(double[] gyro) { if (debugActivity != null) debugActivity.updateGyro(gyro); }
    public void call_updateConnectionStatus() { if (debugActivity != null) debugActivity.updateConnectionStatus(connectionStatus); }
    public void call_onConnected() {
        if (confirmationActivity != null) { confirmationActivity.changeChosenElement(Gesture.NONE); confirmationActivity.showCenterElement(); }
        if (askAddressSelection != null) askAddressSelection.changeChosenElement(Gesture.NONE);
        if (askBasicneedsSelection != null) askBasicneedsSelection.changeChosenElement(Gesture.NONE);
        if (askItemsSelection != null) askItemsSelection.changeChosenElement(Gesture.NONE);
        if (askLocalisationsSelection != null) askLocalisationsSelection.changeChosenElement(Gesture.NONE);
        if (askMainSelection != null) askMainSelection.changeChosenElement(Gesture.NONE);
        if (askPersonsSelection != null) askPersonsSelection.changeChosenElement(Gesture.NONE);
        if (debugActivity != null) debugActivity.onConnected();
        if (emotionLevel != null) emotionLevel.changeChosenElement(Gesture.NONE);
        if (feelingsActivity != null) feelingsActivity.changeChosenElement(Gesture.NONE);
        if (finalActivity != null) finalActivity.changeChosenElement(Gesture.NONE);
        if (mainActivity != null) mainActivity.changeChosenElement(Gesture.NONE);
        if (painHeadSelection != null) painHeadSelection.changeChosenElement(Gesture.NONE);
        if (painHandSelection != null) painHandSelection.changeChosenElement(Gesture.NONE);
        if (painLeftRightSelection != null) painLeftRightSelection.changeChosenElement(Gesture.NONE);
        if (painLegSelection != null) painLegSelection.changeChosenElement(Gesture.NONE);
        if (painMainSelection != null) painMainSelection.changeChosenElement(Gesture.NONE);
        if (painTorsoSelection != null) painTorsoSelection.changeChosenElement(Gesture.NONE);
    }
    public void call_onDisconnected() {
        if (confirmationActivity != null) { confirmationActivity.unchoseElement(); confirmationActivity.hideCenterElement(); }
        if (askAddressSelection != null) askAddressSelection.unchoseElement();
        if (askBasicneedsSelection != null) askBasicneedsSelection.unchoseElement();
        if (askItemsSelection != null) askItemsSelection.unchoseElement();
        if (askLocalisationsSelection != null) askLocalisationsSelection.unchoseElement();
        if (askMainSelection != null) askMainSelection.unchoseElement();
        if (askPersonsSelection != null) askPersonsSelection.unchoseElement();
        if (debugActivity != null) debugActivity.onDisconnected();
        if (emotionLevel != null) emotionLevel.unchoseElement();
        if (feelingsActivity != null) feelingsActivity.unchoseElement();
        if (finalActivity != null) finalActivity.unchoseElement();
        if (mainActivity != null) mainActivity.unchoseElement();
        if (painHeadSelection != null) painHeadSelection.unchoseElement();
        if (painHandSelection != null) painHandSelection.unchoseElement();
        if (painLeftRightSelection != null) painLeftRightSelection.unchoseElement();
        if (painLegSelection != null) painLegSelection.unchoseElement();
        if (painMainSelection != null) painMainSelection.unchoseElement();
        if (painTorsoSelection != null) painTorsoSelection.unchoseElement();
    }
    public void call_updateMode(boolean[] modes) { if (debugActivity != null) debugActivity.updateMode(modes); }


}
