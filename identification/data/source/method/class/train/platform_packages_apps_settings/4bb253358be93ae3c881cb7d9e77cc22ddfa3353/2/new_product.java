protected String getSummary(int subId, boolean isDefaultForData) {
        final int callsDefaultSubId = SubscriptionManager.getDefaultVoiceSubscriptionId();
        final int smsDefaultSubId = SubscriptionManager.getDefaultSmsSubscriptionId();

        String line1 = null;
        if (subId == callsDefaultSubId && subId == smsDefaultSubId) {
            line1 = mContext.getString(R.string.default_for_calls_and_sms);
        } else if (subId == callsDefaultSubId) {
            line1 = mContext.getString(R.string.default_for_calls);
        } else if (subId == smsDefaultSubId) {
            line1 = mContext.getString(R.string.default_for_sms);
        }

        String line2 = null;
        if (isDefaultForData) {
            final TelephonyManager telMgrForSub = mContext.getSystemService(
                    TelephonyManager.class).createForSubscriptionId(subId);
            final boolean dataEnabled = telMgrForSub.isDataEnabled();
            if (dataEnabled && activeNetworkIsCellular()) {
                line2 = mContext.getString(R.string.mobile_data_active);
            } else if (!dataEnabled) {
                line2 = mContext.getString(R.string.mobile_data_off);
            } else {
                line2 = mContext.getString(R.string.default_for_mobile_data);
            }
        }

        if (line1 != null && line2 != null) {
            return String.join(System.lineSeparator(), line1, line2);
        } else if (line1 != null) {
            return line1;
        } else if (line2 != null) {
            return line2;
        } else {
            return mContext.getString(R.string.subscription_available);
        }
    }