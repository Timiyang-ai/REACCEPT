public static int getConnectedDevicesSummaryResourceId(Context context) {
        final NfcPreferenceController nfcPreferenceController =
                new NfcPreferenceController(context, NfcPreferenceController.KEY_TOGGLE_NFC);

        return getConnectedDevicesSummaryResourceId(nfcPreferenceController,
                isDrivingModeAvailable(context));
    }