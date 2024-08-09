@TestTargetNew(
        level = TestLevel.COMPLETE,
        method = "read",
        args = {}
    )    
    public void test_read() throws IOException {
        lnr = new LineNumberReader(new StringReader(text));

        int c = lnr.read();
        assertEquals("Test 1: Read returned incorrect character;", 
                '0', c);
        lnr.read();
        assertEquals("Test 2: Read failed to increase the line number;",
                1, lnr.getLineNumber());

        lnr.close();
        try {
            lnr.read();
            fail("Test 3: IOException expected.");
        } catch (IOException e) {
            // Expected.
        }
    }