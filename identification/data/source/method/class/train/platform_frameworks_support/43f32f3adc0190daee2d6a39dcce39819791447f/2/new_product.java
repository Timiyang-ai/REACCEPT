public final void removeAction(@IdRes int actionId) {
        if (mActions == null) {
            return;
        }
        mActions.delete(actionId);
    }