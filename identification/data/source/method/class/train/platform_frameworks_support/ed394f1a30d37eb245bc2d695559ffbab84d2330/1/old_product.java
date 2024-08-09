@VisibleForTesting
    void resizeSelection(Point relativePointer) {
        mPointer = mHost.createAbsolutePoint(relativePointer);
        updateModel();
    }