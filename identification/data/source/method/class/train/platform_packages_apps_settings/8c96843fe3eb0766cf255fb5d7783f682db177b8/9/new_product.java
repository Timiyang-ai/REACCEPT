@Override
    public void onReceive(Context context, Intent intent) {
        final String action = intent.getAction();
        final String key = intent.getStringExtra(EXTRA_SLICE_KEY);
        final boolean isPlatformSlice = intent.getBooleanExtra(EXTRA_SLICE_PLATFORM_DEFINED,
                false /* default */);

        switch (action) {
            case ACTION_TOGGLE_CHANGED:
                final boolean isChecked = intent.getBooleanExtra(Slice.EXTRA_TOGGLE_STATE, false);
                handleToggleAction(context, key, isChecked, isPlatformSlice);
                break;
            case ACTION_SLIDER_CHANGED:
                final int newPosition = intent.getIntExtra(Slice.EXTRA_RANGE_VALUE, -1);
                handleSliderAction(context, key, newPosition, isPlatformSlice);
                break;
            case ACTION_WIFI_SLICE_CHANGED:
                WifiSliceBuilder.handleUriChange(context, intent);
                break;
            case ACTION_WIFI_CALLING_CHANGED:
                FeatureFactory.getFactory(context)
                        .getSlicesFeatureProvider()
                        .getNewWifiCallingSliceHelper(context)
                        .handleWifiCallingChanged(intent);
                break;
            default:
                final String uriString = intent.getStringExtra(SliceBroadcastRelay.EXTRA_URI);
                if (!TextUtils.isEmpty(uriString)) {
                    final Uri uri = Uri.parse(uriString);
                    context.getContentResolver().notifyChange(uri, null /* observer */);
                }
        }
    }