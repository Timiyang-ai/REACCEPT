@Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        String key = intent.getStringExtra(EXTRA_SLICE_KEY);

        switch (action) {
            case ACTION_TOGGLE_CHANGED:
                handleToggleAction(context, key);
                break;
            case ACTION_WIFI_CHANGED:
                WifiManager wm = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
                boolean newState = intent.getBooleanExtra(Slice.EXTRA_TOGGLE_STATE,
                        wm.isWifiEnabled());
                wm.setWifiEnabled(newState);
                // Wait a bit for wifi to update (TODO: is there a better way to do this?)
                Handler h = new Handler();
                h.postDelayed(() -> {
                    Uri uri = SettingsSliceProvider.getUri(SettingsSliceProvider.PATH_WIFI);
                    context.getContentResolver().notifyChange(uri, null);
                }, 1000);
                break;
        }
    }