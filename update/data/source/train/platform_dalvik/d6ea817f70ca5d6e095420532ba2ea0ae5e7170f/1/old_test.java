@TestTargetNew(
        level = TestLevel.COMPLETE,
        notes = "",
        method = "flush",
        args = {}
    )
    public void test_flush() {
        // Test for method void java.io.OutputStreamWriter.flush()
        try {
            char[] buf = new char[testString.length()];
            osw.write(testString, 0, testString.length());
            osw.flush();
            openInputStream();
            isr.read(buf, 0, buf.length);
            assertTrue("Test 1: Characters have not been flushed.", 
                       new String(buf, 0, buf.length).equals(testString));
        } catch (Exception e) {
            fail("Test 1: Unexpected exception: " + e.getMessage());
        }
        
        fos.setThrowsException(true);
        try {
            osw.flush();
            fail("Test 2: IOException expected.");
        } catch (IOException e) {
            // Expected
        }
        fos.setThrowsException(false);
    }