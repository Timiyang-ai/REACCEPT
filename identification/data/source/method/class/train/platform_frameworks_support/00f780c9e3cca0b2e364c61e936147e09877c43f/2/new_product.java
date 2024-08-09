void updateBounds(Rect bounds) {
        final ArrayList<ChildDrawable> children = mState.mChildren;
        for (int i = 0; i < children.size(); i++) {
            ChildDrawable childDrawable = children.get(i);
            childDrawable.updateBounds(bounds);
        }
    }