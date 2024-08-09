public boolean isScreenLockVisible(ScreenLockType type) {
        switch (type) {
            case NONE:
                return !mContext.getResources().getBoolean(R.bool.config_hide_none_security_option);
            case SWIPE:
                return !mContext.getResources().getBoolean(R.bool.config_hide_swipe_security_option)
                    // Swipe doesn't make sense for profiles.
                    && mUserId == UserHandle.myUserId();
            case MANAGED:
                return mManagedPasswordProvider.isManagedPasswordChoosable();
        }
        return true;
    }