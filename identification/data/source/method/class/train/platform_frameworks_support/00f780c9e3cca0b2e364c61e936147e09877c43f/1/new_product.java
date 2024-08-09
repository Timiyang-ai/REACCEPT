void updateBounds(Rect bounds) {
            mBoundsRule.calculateBounds(bounds, adjustedBounds);
            mDrawable.setBounds(adjustedBounds);
        }