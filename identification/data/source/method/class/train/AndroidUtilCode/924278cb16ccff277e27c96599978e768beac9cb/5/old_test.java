    @Test
    public void remove() {
        assertNotNull(CACHE_DISK_UTILS1.getString("string1"));
        assertTrue(CACHE_DISK_UTILS1.remove("string1"));
        assertNull(CACHE_DISK_UTILS1.getString("string1"));

        assertNotNull(CACHE_DISK_UTILS2.getString("string2"));
        CACHE_DISK_UTILS2.remove("string2");
        assertNull(CACHE_DISK_UTILS2.getString("string2"));
    }