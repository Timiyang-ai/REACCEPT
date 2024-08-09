    @Test
    public void getDrawable() {
        String bitmapString = "Bitmap (100 x 100) compressed as PNG with quality 100";
        assertArrayEquals(
                ImageUtils.bitmap2Bytes(BITMAP, Bitmap.CompressFormat.PNG),
                ImageUtils.drawable2Bytes(CACHE_DISK_UTILS1.getDrawable("drawable1"), Bitmap.CompressFormat.PNG)
        );
        assertArrayEquals(
                ImageUtils.bitmap2Bytes(BITMAP, Bitmap.CompressFormat.PNG),
                ImageUtils.drawable2Bytes(CACHE_DISK_UTILS1.getDrawable("drawable1", null), Bitmap.CompressFormat.PNG)
        );
        assertNull(CACHE_DISK_UTILS1.getDrawable("drawable2", null));

        assertArrayEquals(
                ImageUtils.bitmap2Bytes(BITMAP, Bitmap.CompressFormat.PNG),
                ImageUtils.drawable2Bytes(CACHE_DISK_UTILS2.getDrawable("drawable2"), Bitmap.CompressFormat.PNG)
        );
        assertArrayEquals(
                ImageUtils.bitmap2Bytes(BITMAP, Bitmap.CompressFormat.PNG),
                ImageUtils.drawable2Bytes(CACHE_DISK_UTILS2.getDrawable("drawable2", null), Bitmap.CompressFormat.PNG)
        );
        assertNull(CACHE_DISK_UTILS2.getDrawable("drawable1", null));
    }