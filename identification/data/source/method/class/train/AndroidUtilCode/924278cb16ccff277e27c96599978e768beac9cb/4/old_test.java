    @Test
    public void clear() {
        assertNotNull(CACHE_DISK_UTILS1.getBytes("bytes1"));
        assertNotNull(CACHE_DISK_UTILS1.getString("string1"));
        assertNotNull(CACHE_DISK_UTILS1.getJSONObject("jsonObject1"));
        assertNotNull(CACHE_DISK_UTILS1.getJSONArray("jsonArray1"));
        assertNotNull(CACHE_DISK_UTILS1.getBitmap("bitmap1"));
        assertNotNull(CACHE_DISK_UTILS1.getDrawable("drawable1"));
        assertNotNull(CACHE_DISK_UTILS1.getParcelable("parcelable1", ParcelableTest.CREATOR));
        assertNotNull(CACHE_DISK_UTILS1.getSerializable("serializable1"));
        CACHE_DISK_UTILS1.clear();
        assertNull(CACHE_DISK_UTILS1.getBytes("bytes1"));
        assertNull(CACHE_DISK_UTILS1.getString("string1"));
        assertNull(CACHE_DISK_UTILS1.getJSONObject("jsonObject1"));
        assertNull(CACHE_DISK_UTILS1.getJSONArray("jsonArray1"));
        assertNull(CACHE_DISK_UTILS1.getBitmap("bitmap1"));
        assertNull(CACHE_DISK_UTILS1.getDrawable("drawable1"));
        assertNull(CACHE_DISK_UTILS1.getParcelable("parcelable1", ParcelableTest.CREATOR));
        assertNull(CACHE_DISK_UTILS1.getSerializable("serializable1"));
        assertEquals(0, CACHE_DISK_UTILS1.getCacheSize());
        assertEquals(0, CACHE_DISK_UTILS1.getCacheCount());


        assertNotNull(CACHE_DISK_UTILS2.getBytes("bytes2"));
        assertNotNull(CACHE_DISK_UTILS2.getString("string2"));
        assertNotNull(CACHE_DISK_UTILS2.getJSONObject("jsonObject2"));
        assertNotNull(CACHE_DISK_UTILS2.getJSONArray("jsonArray2"));
        assertNotNull(CACHE_DISK_UTILS2.getBitmap("bitmap2"));
        assertNotNull(CACHE_DISK_UTILS2.getDrawable("drawable2"));
        assertNotNull(CACHE_DISK_UTILS2.getParcelable("parcelable2", ParcelableTest.CREATOR));
        assertNotNull(CACHE_DISK_UTILS2.getSerializable("serializable2"));
        CACHE_DISK_UTILS2.clear();
        assertNull(CACHE_DISK_UTILS2.getBytes("bytes2"));
        assertNull(CACHE_DISK_UTILS2.getString("string2"));
        assertNull(CACHE_DISK_UTILS2.getJSONObject("jsonObject2"));
        assertNull(CACHE_DISK_UTILS2.getJSONArray("jsonArray2"));
        assertNull(CACHE_DISK_UTILS2.getBitmap("bitmap2"));
        assertNull(CACHE_DISK_UTILS2.getDrawable("drawable2"));
        assertNull(CACHE_DISK_UTILS2.getParcelable("parcelable2", ParcelableTest.CREATOR));
        assertNull(CACHE_DISK_UTILS2.getSerializable("serializable2"));
        assertEquals(0, CACHE_DISK_UTILS2.getCacheSize());
        assertEquals(0, CACHE_DISK_UTILS2.getCacheCount());
    }