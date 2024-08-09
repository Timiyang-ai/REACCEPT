@TestTargetNew(
        level = TestLevel.COMPLETE,
        notes = "",
        method = "reset",
        args = {}
    )
    public void test_reset() {
        InputStream is = new ByteArrayInputStream(new byte[10]);
        InflaterInputStream iis = new InflaterInputStream(is);
        try {
            iis.reset();
            fail("Should throw IOException");
        } catch (IOException e) {
            // correct
        }
    }