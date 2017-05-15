/*
 * This example uses the telephony manager to monitor call state.
 * Use the emulator extended control panel to emulate an incoming call.
 * Notice the permissions in the Manifest. They are both considered dangerous.
 * Go into the emulator's Settings for this app and enable both.
 */
package com.course.example.telmanager;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.CellLocation;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class TelManager extends Activity {

    private TextView telMgrOutput;

    @Override
    public void onCreate(final Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.main);

        telMgrOutput = (TextView) findViewById(R.id.telmgroutput);
        Log.d("TelManager", "TelephonyManagerExample onCreate");
    }

    @Override
    public void onStart() {
        super.onStart();

        // create TelephonyManager object
        final TelephonyManager telMgr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

        // PhoneStateListener
        PhoneStateListener phoneStateListener = new PhoneStateListener() {

            @Override
            public void onCallStateChanged(final int state, final String incomingNumber) {
                super.onCallStateChanged(state, incomingNumber);
                telMgrOutput.append("State Change" + "\n");
                telMgrOutput.append(getTelephonyOverview(telMgr) + "\n\n\n");
                Log.d("TelManager", "phoneState updated - incoming number - " + incomingNumber);
            }
        };

        //put listener on tel manager
        telMgr.listen(phoneStateListener, PhoneStateListener.LISTEN_CALL_STATE);

        //write tel overview data on UI
        String telephonyOverview = getTelephonyOverview(telMgr);
        telMgrOutput.append(telephonyOverview + "\n\n\n");
    }


     // Parse TelephonyManager values into String.
    public  String getTelephonyOverview(final TelephonyManager telMgr) {
        int callState = telMgr.getCallState();
        String callStateString = "NA";
        switch (callState) {
            case TelephonyManager.CALL_STATE_IDLE:
                callStateString = "IDLE";
                break;
            case TelephonyManager.CALL_STATE_OFFHOOK:
                callStateString = "OFFHOOK";
                break;
            case TelephonyManager.CALL_STATE_RINGING:
                callStateString = "RINGING";
                break;
            default:
                break;
        }
        Log.d("TelManager", "Call State = " + callStateString);
        Toast.makeText(this, callStateString, Toast.LENGTH_LONG).show();

        CellLocation cellLocation = telMgr.getCellLocation();
        String cellLocationString = cellLocation.toString();

        String deviceId = telMgr.getDeviceId();
        String deviceSoftwareVersion = telMgr.getDeviceSoftwareVersion();

        String line1Number = telMgr.getLine1Number();

        String networkCountryIso = telMgr.getNetworkCountryIso();
        String networkOperator = telMgr.getNetworkOperator();
        String networkOperatorName = telMgr.getNetworkOperatorName();

        int phoneType = telMgr.getPhoneType();
        String phoneTypeString = "NA";
        switch (phoneType) {
            case TelephonyManager.PHONE_TYPE_GSM:
                phoneTypeString = "GSM";
                break;
            case TelephonyManager.PHONE_TYPE_NONE:
                phoneTypeString = "NONE";
                break;
        }

        String simCountryIso = telMgr.getSimCountryIso();
        String simOperator = telMgr.getSimOperator();
        String simOperatorName = telMgr.getSimOperatorName();
        String simSerialNumber = telMgr.getSimSerialNumber();
        String simSubscriberId = telMgr.getSubscriberId();
        int simState = telMgr.getSimState();
        String simStateString = "NA";
        switch (simState) {
            case TelephonyManager.SIM_STATE_ABSENT:
                simStateString = "ABSENT";
                break;
            case TelephonyManager.SIM_STATE_NETWORK_LOCKED:
                simStateString = "NETWORK_LOCKED";
                break;
            case TelephonyManager.SIM_STATE_PIN_REQUIRED:
                simStateString = "PIN_REQUIRED";
                break;
            case TelephonyManager.SIM_STATE_PUK_REQUIRED:
                simStateString = "PUK_REQUIRED";
                break;
            case TelephonyManager.SIM_STATE_READY:
                simStateString = "STATE_READY";
                break;
            case TelephonyManager.SIM_STATE_UNKNOWN:
                simStateString = "STATE_UNKNOWN";
                break;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("telMgr - ");
        sb.append("  \ncallState = " + callStateString);
        sb.append("  \ncellLocationString = " + cellLocationString);
        sb.append("  \ndeviceId = " + deviceId);
        sb.append("  \ndeviceSoftwareVersion = " + deviceSoftwareVersion);
        sb.append("  \nline1Number = " + line1Number);
        sb.append("  \nnetworkCountryIso = " + networkCountryIso);
        sb.append("  \nnetworkOperator = " + networkOperator);
        sb.append("  \nnetworkOperatorName = " + networkOperatorName);
        sb.append("  \nphoneTypeString = " + phoneTypeString);
        sb.append("  \nsimCountryIso = " + simCountryIso);
        sb.append("  \nsimOperator = " + simOperator);
        sb.append("  \nsimOperatorName = " + simOperatorName);
        sb.append("  \nsimSerialNumber = " + simSerialNumber);
        sb.append("  \nsimSubscriberId = " + simSubscriberId);
        sb.append("  \nsimStateString = " + simStateString);

        return sb.toString();
    }//end getTelephonyOverview method
}