public static int getConnectedDevicesSummaryResourceId(Context context) {
        final NfcPreferenceController nfcPreferenceController =
                new NfcPreferenceController(context);
        final boolean isDrivingModeAvailable = false;

        return getConnectedDevicesSummaryResourceId(nfcPreferenceController,
                isDrivingModeAvailable);
    }