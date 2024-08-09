@Override
    public void onReceive(Context context, Intent intent) {
        final String action = intent.getAction();
        final String key = intent.getStringExtra(EXTRA_SLICE_KEY);
        final boolean isPlatformDefined = intent.getBooleanExtra(EXTRA_SLICE_PLATFORM_DEFINED,
                false /* default */);

        switch (action) {
            case ACTION_TOGGLE_CHANGED:
                handleToggleAction(context, key, isPlatformDefined);
                break;
            case ACTION_SLIDER_CHANGED:
                int newPosition = intent.getIntExtra(Slice.EXTRA_RANGE_VALUE, -1);
                handleSliderAction(context, key, newPosition);
                break;
            case ACTION_WIFI_CHANGED:
                WifiManager wm = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
                boolean newState = intent.getBooleanExtra(Slice.EXTRA_TOGGLE_STATE,
                        wm.isWifiEnabled());
                wm.setWifiEnabled(newState);
                // Wait a bit for wifi to update (TODO: is there a better way to do this?)
                Handler h = new Handler();
                h.postDelayed(() -> {
                    Uri uri = SliceBuilderUtils.getUri(SettingsSliceProvider.PATH_WIFI,
                            false /* isPlatformSlice */);
                    context.getContentResolver().notifyChange(uri, null);
                }, 1000);
                break;
        }
    }