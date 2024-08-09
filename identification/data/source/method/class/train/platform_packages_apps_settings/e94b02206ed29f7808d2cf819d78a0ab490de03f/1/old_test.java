    @Test
    public void handleUriChange_updatesBluetooth() {
        final BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
        final Intent intent = new Intent();
        intent.putExtra(android.app.slice.Slice.EXTRA_TOGGLE_STATE, true);
        adapter.disable()/* enabled */;

        BluetoothSliceBuilder.handleUriChange(mContext, intent);

        assertThat(adapter.isEnabled()).isTrue();
    }