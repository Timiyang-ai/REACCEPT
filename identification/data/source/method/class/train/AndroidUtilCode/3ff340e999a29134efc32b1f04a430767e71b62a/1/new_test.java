    @Test
    public void getString() {
        assertEquals(STRING, CACHE_DISK_UTILS1.getString("string1"));
        assertEquals(STRING, CACHE_DISK_UTILS1.getString("string1", null));
        assertNull(CACHE_DISK_UTILS1.getString("string2", null));

        assertEquals(STRING, CACHE_DISK_UTILS2.getString("string2"));
        assertEquals(STRING, CACHE_DISK_UTILS2.getString("string2", null));
        assertNull(CACHE_DISK_UTILS2.getString("string1", null));
    }