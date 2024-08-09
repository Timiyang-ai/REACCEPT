@TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "getTimestamp",
          methodArgs = {}
        )
    })
    public void testGetTimestamp() {
        assertNull(new CodeSigner(cpath, null).getTimestamp());
        assertSame(new CodeSigner(cpath, ts).getTimestamp(), ts);
    }