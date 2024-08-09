public static void handleUriChange(Context context, Intent intent) {
        final boolean newBluetoothState = intent.getBooleanExtra(EXTRA_TOGGLE_STATE, false);
        final BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();

        if (newBluetoothState) {
            adapter.enable();
        } else {
            adapter.disable();
        }
        // Do not notifyChange on Uri. The service takes longer to update the current value than it
        // does for the Slice to check the current value again. Let {@link SliceBroadcastRelay}
        // handle it.
    }