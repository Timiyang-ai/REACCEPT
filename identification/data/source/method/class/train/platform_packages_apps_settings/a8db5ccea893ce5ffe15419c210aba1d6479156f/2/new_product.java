@VisibleForTesting
    boolean maybeEnforceRestrictions() {
        EnforcedAdmin admin = getEnforcedAdmin(mRestrictionUtils, mContext);
        mSwitchWidget.setDisabledByAdmin(admin);
        if (admin != null) {
            mSwitchWidget.setChecked(false);
            if (mSwitch != null) {
                mSwitch.setEnabled(false);
                mSwitch.setChecked(false);
            }
        }
        return admin != null;
    }