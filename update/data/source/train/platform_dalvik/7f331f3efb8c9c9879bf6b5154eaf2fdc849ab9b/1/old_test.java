@TestTargetNew(
        level = TestLevel.COMPLETE,
        notes = "",
        method = "available",
        args = {}
    )
    public void test_available() throws IOException {
        InputStream is = Support_Resources.getStream("hyts_available.tst");
        InflaterInputStream iis = new InflaterInputStream(is);

        int available;
        int read;
        for (int i = 0; i < 11; i++) {
            read = iis.read();
            available = iis.available();
            if (read == -1) {
                assertEquals("Bytes Available Should Return 0 ", 0, available);
            } else {
                assertEquals("Bytes Available Should Return 1.", 1, available);
            }
        }

        iis.close();
        try {
            iis.available();
            fail("available after close should throw IOException.");
        } catch (IOException e) {
            // Expected
        }
    }