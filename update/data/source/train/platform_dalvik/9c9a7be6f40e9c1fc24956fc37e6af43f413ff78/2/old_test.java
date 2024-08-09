@TestTargetNew(
        level = TestLevel.COMPLETE,
        notes = "",
        method = "getCreationTime",
        args = {}
    )
    @KnownFailure("Time returned is corrupted")
    @AndroidOnly("Uses bks key store. Change useBKS to false to run on the RI")
    public void test_getCreationTime() {
        try {
            // check if creation time was in the last 10 seconds
            long diff = new Date().getTime() - clientSession.getCreationTime();
            assertTrue (diff < 10000);
        } catch (Exception ex) {
            fail("Unexpected exception " + ex);
        }
    }