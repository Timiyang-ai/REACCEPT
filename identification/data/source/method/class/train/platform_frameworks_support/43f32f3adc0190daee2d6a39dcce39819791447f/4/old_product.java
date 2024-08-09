public void putAction(@IdRes int actionId, @NonNull NavAction action) {
        if (actionId == 0) {
            throw new IllegalArgumentException("Cannot have an action with actionId 0");
        }
        if (mActions == null) {
            mActions = new SparseArrayCompat<>();
        }
        mActions.put(actionId, action);
    }