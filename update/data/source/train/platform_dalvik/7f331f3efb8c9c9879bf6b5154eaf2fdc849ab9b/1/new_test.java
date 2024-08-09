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
        for (int i = 0; i < 11; i++) {
            iis.read();
            available = iis.available();
            if (available == 0) {
                assertEquals("Expected no more bytes to read", -1, iis.read());
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