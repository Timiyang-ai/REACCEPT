@TestTargetNew(
        level = TestLevel.COMPLETE,
        notes = "",
        method = "hashCode",
        args = {}
    )
    public void test_hashCode() throws Exception {
        URL url = new java.net.URL("file:///test");
        CodeSource cs = new CodeSource(url, (Certificate[]) null);
        assertEquals("Did not get expected hashCode!", cs.hashCode(), url
                .hashCode());
    }