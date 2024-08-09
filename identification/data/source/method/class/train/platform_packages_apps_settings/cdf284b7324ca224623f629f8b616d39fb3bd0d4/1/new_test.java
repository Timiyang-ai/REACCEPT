    @Test
    public void setMobileDataEnabled_disableOtherSubscriptions() {
        MobileNetworkUtils.setMobileDataEnabled(mContext, SUB_ID_1, true, true);

        verify(mTelephonyManager).setDataEnabled(true);
        verify(mTelephonyManager2).setDataEnabled(false);
    }