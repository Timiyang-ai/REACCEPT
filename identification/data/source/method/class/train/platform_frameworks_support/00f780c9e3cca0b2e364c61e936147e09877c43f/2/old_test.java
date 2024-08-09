    @Test
    public void updateBounds_noBoundsRule() {
        CompositeDrawable parentDrawable = new CompositeDrawable();
        FitWidthBitmapDrawable drawable = new FitWidthBitmapDrawable();
        drawable.setBitmap(mBitmap);
        Rect bounds = new Rect(0, 0, WIDTH, HEIGHT);
        parentDrawable.addChildDrawable(drawable);
        parentDrawable.updateBounds(bounds);

        Rect adjustedBounds = drawable.getBounds();
        assertEquals(bounds, adjustedBounds);
    }