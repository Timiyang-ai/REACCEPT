    @Test
    public void parseCacheHeaders_noHeaders() {
        Cache.Entry entry = HttpHeaderParser.parseCacheHeaders(response);

        assertNotNull(entry);
        assertNull(entry.etag);
        assertEquals(0, entry.serverDate);
        assertEquals(0, entry.lastModified);
        assertEquals(0, entry.ttl);
        assertEquals(0, entry.softTtl);
    }