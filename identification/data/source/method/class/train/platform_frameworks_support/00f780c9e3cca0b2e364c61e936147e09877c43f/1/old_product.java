void updateBounds(Rect bounds) {
            boundsRule.calculateBounds(bounds, adjustedBounds);
            mDrawable.setBounds(adjustedBounds);
        }