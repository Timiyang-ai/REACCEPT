void updateBounds(Rect bounds) {
        for (int i = 0; i < children.size(); i++) {
            ChildDrawable childDrawable = children.get(i);
            childDrawable.updateBounds(bounds);
        }
    }