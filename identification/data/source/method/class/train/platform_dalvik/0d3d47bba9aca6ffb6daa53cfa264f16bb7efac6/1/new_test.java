@TestTargetNew(
        level = TestLevel.COMPLETE,
        method = "size",
        args = {}
    )
    public void test_size() throws IOException {
        assertEquals(6, zfile.size());
        zfile.close();
        try {
            zfile.size();
            fail("IllegalStateException expected");
        } catch (IllegalStateException expected) {
        }
    }