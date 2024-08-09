@TestTargetNew(
        level = TestLevel.COMPLETE,
        method = "readLine",
        args = {}
    )     
    public void test_readLine() throws IOException {
        lnr = new LineNumberReader(new StringReader(text));
        assertEquals("Returned incorrect line number", 0, lnr.getLineNumber());
        String line = null;
        lnr.readLine();
        line = lnr.readLine();
        assertEquals("Test 1: Returned incorrect string;", "1", line);
        assertTrue("Test 2: Returned incorrect line number:" + lnr.getLineNumber(),
                lnr.getLineNumber() == 2);

        lnr.close();
        try {
            lnr.readLine();
            fail("Test 3: IOException expected.");
        } catch (IOException e) {
            // Expected.
        }
    }