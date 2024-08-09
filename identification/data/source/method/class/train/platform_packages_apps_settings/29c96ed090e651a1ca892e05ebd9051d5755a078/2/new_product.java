public static int getConnectedDevicesSummaryResourceId(Context context) {
        final NfcPreferenceController nfcPreferenceController =
                new NfcPreferenceController(context);

        return getConnectedDevicesSummaryResourceId(nfcPreferenceController,
                isDrivingModeAvailable(context));
    }