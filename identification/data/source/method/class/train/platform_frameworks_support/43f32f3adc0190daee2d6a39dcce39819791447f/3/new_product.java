public final void putAction(@IdRes int actionId, @IdRes int destId) {
        putAction(actionId, new NavAction(destId));
    }