public final void detachView() {
        if (!isViewAttached()) {
            TiLog.v(TAG, "not calling onDetachView(), not woken up");
            return;
        }
        moveToState(State.VIEW_DETACHED, false);
        mCalled = false;
        TiLog.v(TAG, "deprecated onSleep()");
        onSleep();
        if (!mCalled) {
            throw new SuperNotCalledException("Presenter " + this
                    + " did not call through to super.onSleep()");
        }
        mCalled = false;
        TiLog.v(TAG, "onDetachView()");
        onDetachView();
        if (!mCalled) {
            throw new SuperNotCalledException("Presenter " + this
                    + " did not call through to super.onDetachView()");
        }

        moveToState(State.VIEW_DETACHED, true);
        mView = null;
    }