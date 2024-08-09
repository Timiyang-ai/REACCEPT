@TestTargetNew(
        level = TestLevel.COMPLETE,
        notes = "Verifies getEncoding() method.",
        method = "getEncoding",
        args = {}
    )       
    public void test_getEncoding() {
        // Test for method java.lang.String
        // java.io.InputStreamReader.getEncoding()
        try {
            is = new InputStreamReader(fis, "8859_1");
        } catch (UnsupportedEncodingException e) {
            assertEquals("Returned incorrect encoding", 
                    "8859_1", is.getEncoding());
        }
    }