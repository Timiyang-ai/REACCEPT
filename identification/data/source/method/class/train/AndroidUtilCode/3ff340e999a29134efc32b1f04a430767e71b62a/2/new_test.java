    @Test
    public void getJSONArray() {
        assertEquals(JSON_ARRAY.toString(), CACHE_DISK_UTILS1.getJSONArray("jsonArray1").toString());
        assertEquals(JSON_ARRAY.toString(), CACHE_DISK_UTILS1.getJSONArray("jsonArray1", null).toString());
        assertNull(CACHE_DISK_UTILS1.getJSONArray("jsonArray2", null));

        assertEquals(JSON_ARRAY.toString(), CACHE_DISK_UTILS2.getJSONArray("jsonArray2").toString());
        assertEquals(JSON_ARRAY.toString(), CACHE_DISK_UTILS2.getJSONArray("jsonArray2", null).toString());
        assertNull(CACHE_DISK_UTILS2.getJSONArray("jsonArray1", null));
    }