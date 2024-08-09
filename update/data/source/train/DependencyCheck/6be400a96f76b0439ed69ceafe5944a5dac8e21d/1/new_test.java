@Test
    public void testDetermineIdentifiers() throws Exception {
        callDetermieIdentifiers("eclipse", "jetty", "9.4.8.v20171121", "cpe:/a:eclipse:jetty:9.4.8");
        callDetermieIdentifiers("openssl", "openssl", "1.0.1c", "cpe:/a:openssl:openssl:1.0.1c");
    }