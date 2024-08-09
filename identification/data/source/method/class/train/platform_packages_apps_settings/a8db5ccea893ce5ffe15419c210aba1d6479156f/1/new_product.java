@VisibleForTesting
    boolean maybeEnforceRestrictions() {
        EnforcedAdmin admin = getEnforcedAdmin(mRestrictionUtils, mContext);
        mSwitchController.setDisabledByAdmin(admin);
        if (admin != null) {
            mSwitchController.setChecked(false);
            mSwitchController.setEnabled(false);
        }
        return admin != null;
    }