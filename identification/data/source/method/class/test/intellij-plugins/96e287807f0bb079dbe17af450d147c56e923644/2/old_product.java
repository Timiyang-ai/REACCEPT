public synchronized void modelChanged() {
        for (TapestryModelChangeListener listener : _tapestryModelChangeListeners)
            listener.modelChanged();
    }