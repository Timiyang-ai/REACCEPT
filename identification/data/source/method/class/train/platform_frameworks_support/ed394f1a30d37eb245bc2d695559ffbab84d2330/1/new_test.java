    private void resizeSelection(Point p) {
        mModel.resizeSelection(p);
        mSelectionPoint = mHost.createAbsolutePoint(p);
    }