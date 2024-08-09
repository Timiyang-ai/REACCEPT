@TestTargetNew(
        level = TestLevel.COMPLETE,
        method = "size",
        args = {}
    )
    @KnownFailure("IllegalStateException not thrown when using ZipFile.size() "
            + "after close().")
    public void test_size() throws IOException {
        assertEquals(6, zfile.size());
        zfile.close();
        try {
            zfile.size();
            fail("IllegalStateException expected"); // Android fails here!
        } catch (IllegalStateException ee) {
            // expected
        }
    }