private boolean canShareNetwork() {
        return mAccessPoint.getConfig() != null &&
                WifiDppUtils.isSupportConfiguratorQrCodeGenerator(mContext, mAccessPoint);
    }