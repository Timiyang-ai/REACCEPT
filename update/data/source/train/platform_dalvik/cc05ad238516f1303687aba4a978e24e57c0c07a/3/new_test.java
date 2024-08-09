@TestTargetNew(
        level = TestLevel.COMPLETE,
        notes = "",
        method = "getTimestamp",
        args = {}
    )
    public void testGetTimestamp() {
        assertNull(new CodeSigner(cpath, null).getTimestamp());
        assertSame(new CodeSigner(cpath, ts).getTimestamp(), ts);
    }