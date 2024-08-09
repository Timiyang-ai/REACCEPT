    @Test
    public void toReceivedTimeMillis() throws Exception {
        ProxyHttpHeaderParser parser = new ProxyHttpHeaderParser();

        assertEquals(1504230492763L, parser.getNginxUnit().toReceivedTimeMillis("1504230492.763"));
        assertEquals(1504244246860L, parser.getApacheUnit().toReceivedTimeMillis("1504244246860824"));
        assertEquals(1504230492763L, parser.getAppUnit().toReceivedTimeMillis("1504230492763"));

        // invalid
        assertEquals(0L, parser.getNginxUnit().toReceivedTimeMillis("1504230492.76"));
        assertEquals(0L, parser.getNginxUnit().toReceivedTimeMillis("1504230492.7"));
        assertEquals(0L, parser.getNginxUnit().toReceivedTimeMillis("1504230492."));

        assertEquals(0L, parser.getApacheUnit().toReceivedTimeMillis("150"));
        assertEquals(0L, parser.getApacheUnit().toReceivedTimeMillis("15"));
        assertEquals(0L, parser.getApacheUnit().toReceivedTimeMillis("1"));
        assertEquals(0L, parser.getApacheUnit().toReceivedTimeMillis(""));
        assertEquals(0L, parser.getApacheUnit().toReceivedTimeMillis(null));

        assertEquals(0L, parser.getAppUnit().toReceivedTimeMillis("A"));
        assertEquals(0L, parser.getAppUnit().toReceivedTimeMillis("150B"));
    }