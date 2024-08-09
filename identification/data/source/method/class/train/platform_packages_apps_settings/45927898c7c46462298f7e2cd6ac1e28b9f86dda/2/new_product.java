public boolean isScreenLockVisible(ScreenLockType type) {
        final boolean managedProfile = mUserId != UserHandle.myUserId();
        switch (type) {
            case NONE:
                return !mContext.getResources().getBoolean(R.bool.config_hide_none_security_option)
                    && !managedProfile; // Profiles should use unified challenge instead.
            case SWIPE:
                return !mContext.getResources().getBoolean(R.bool.config_hide_swipe_security_option)
                    && !managedProfile; // Swipe doesn't make sense for profiles.
            case MANAGED:
                return mManagedPasswordProvider.isManagedPasswordChoosable();
            case PIN:
            case PATTERN:
            case PASSWORD:
                // Hide the secure lock screen options if the device doesn't support the secure lock
                // screen feature.
                return mLockPatternUtils.hasSecureLockScreen();
        }
        return true;
    }