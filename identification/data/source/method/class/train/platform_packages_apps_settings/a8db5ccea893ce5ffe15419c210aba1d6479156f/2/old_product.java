@VisibleForTesting
    boolean maybeEnforceRestrictions() {
        EnforcedAdmin admin = mRestrictionUtils.checkIfRestrictionEnforced(
                mContext, UserManager.DISALLOW_BLUETOOTH);
        if (admin == null) {
            admin = mRestrictionUtils.checkIfRestrictionEnforced(
                    mContext, UserManager.DISALLOW_CONFIG_BLUETOOTH);
        }
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