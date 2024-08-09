public final Buffer clear() {
        // BEGIN android-changed
        internalClear();
        // END android-changed
        return this;
    }