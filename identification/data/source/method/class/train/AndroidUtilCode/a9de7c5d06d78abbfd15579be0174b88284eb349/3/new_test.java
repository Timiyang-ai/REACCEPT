    @Test
    public void getBytes() {
        assertEquals(STRING, new String(CACHE_DISK_UTILS1.getBytes("bytes1")));
        assertEquals(STRING, new String(CACHE_DISK_UTILS1.getBytes("bytes1", null)));
        assertNull(CACHE_DISK_UTILS1.getBytes("bytes2", null));

        assertEquals(STRING, new String(CACHE_DISK_UTILS2.getBytes("bytes2")));
        assertEquals(STRING, new String(CACHE_DISK_UTILS2.getBytes("bytes2", null)));
        assertNull(CACHE_DISK_UTILS2.getBytes("bytes1", null));
    }