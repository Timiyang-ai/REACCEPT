public static void setMobileDataEnabled(Context context, int subId, boolean enabled,
            boolean disableOtherSubscriptions) {
        final TelephonyManager telephonyManager = TelephonyManager.from(context)
                .createForSubscriptionId(subId);
        final SubscriptionManager subscriptionManager = context.getSystemService(
                SubscriptionManager.class);
        telephonyManager.setDataEnabled(enabled);

        if (disableOtherSubscriptions) {
            List<SubscriptionInfo> subInfoList =
                    subscriptionManager.getActiveSubscriptionInfoList();
            if (subInfoList != null) {
                for (SubscriptionInfo subInfo : subInfoList) {
                    if (subInfo.getSubscriptionId() != subId) {
                        TelephonyManager.from(context).createForSubscriptionId(
                                subInfo.getSubscriptionId()).setDataEnabled(false);
                    }
                }
            }
        }
    }