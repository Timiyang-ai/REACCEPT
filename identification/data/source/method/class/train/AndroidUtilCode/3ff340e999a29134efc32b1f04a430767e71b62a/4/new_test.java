    @Test
    public void getBitmap() {
        assertArrayEquals(
                ImageUtils.bitmap2Bytes(BITMAP, Bitmap.CompressFormat.PNG),
                ImageUtils.bitmap2Bytes(CACHE_DISK_UTILS1.getBitmap("bitmap1"), Bitmap.CompressFormat.PNG)
        );
        assertArrayEquals(
                ImageUtils.bitmap2Bytes(BITMAP, Bitmap.CompressFormat.PNG),
                ImageUtils.bitmap2Bytes(CACHE_DISK_UTILS1.getBitmap("bitmap1", null), Bitmap.CompressFormat.PNG)
        );
        assertNull(CACHE_DISK_UTILS1.getBitmap("bitmap2", null));

        assertArrayEquals(
                ImageUtils.bitmap2Bytes(BITMAP, Bitmap.CompressFormat.PNG),
                ImageUtils.bitmap2Bytes(CACHE_DISK_UTILS2.getBitmap("bitmap2"), Bitmap.CompressFormat.PNG)
        );
        assertArrayEquals(
                ImageUtils.bitmap2Bytes(BITMAP, Bitmap.CompressFormat.PNG),
                ImageUtils.bitmap2Bytes(CACHE_DISK_UTILS2.getBitmap("bitmap2", null), Bitmap.CompressFormat.PNG)
        );
        assertNull(CACHE_DISK_UTILS2.getBitmap("bitmap1", null));
    }