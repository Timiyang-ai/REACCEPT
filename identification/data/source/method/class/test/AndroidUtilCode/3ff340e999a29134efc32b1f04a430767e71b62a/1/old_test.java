    @Test
    public void getJSONObject() {
        assertEquals(JSON_OBJECT.toString(), CACHE_DISK_UTILS1.getJSONObject("jsonObject1").toString());
        assertEquals(JSON_OBJECT.toString(), CACHE_DISK_UTILS1.getJSONObject("jsonObject1", null).toString());
        assertNull(CACHE_DISK_UTILS1.getJSONObject("jsonObject2", null));

        assertEquals(JSON_OBJECT.toString(), CACHE_DISK_UTILS2.getJSONObject("jsonObject2").toString());
        assertEquals(JSON_OBJECT.toString(), CACHE_DISK_UTILS2.getJSONObject("jsonObject2", null).toString());
        assertNull(CACHE_DISK_UTILS2.getJSONObject("jsonObject1", null));
    }