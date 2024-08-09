public final void putAction(@IdRes int actionId, @NonNull NavAction action) {
        if (!supportsActions()) {
            throw new UnsupportedOperationException("Cannot add action " + actionId + " to "
                    + this + " as it does not support actions, indicating that it is a "
                    + "terminal destination in your navigation graph and will never trigger "
                    + "actions.");
        }
        if (actionId == 0) {
            throw new IllegalArgumentException("Cannot have an action with actionId 0");
        }
        if (mActions == null) {
            mActions = new SparseArrayCompat<>();
        }
        mActions.put(actionId, action);
    }