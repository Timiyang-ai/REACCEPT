private boolean canShareNetwork() {
        return mAccessPoint.getConfig() != null && FeatureFlagUtils.isEnabled(mContext,
                FeatureFlags.WIFI_SHARING);
    }