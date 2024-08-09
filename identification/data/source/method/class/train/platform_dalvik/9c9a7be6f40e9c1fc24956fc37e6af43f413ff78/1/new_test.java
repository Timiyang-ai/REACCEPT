@TestTargetNew(
        level = TestLevel.COMPLETE,
        notes = "",
        method = "getLastAccessedTime",
        args = {}
    )
    @AndroidOnly("Uses bks key store. Change useBKS to false to run on the RI")
    public void test_getLastAccessedTime() {
        try {
            // check if last access time was in the last 10 seconds
            long diff = new Date().getTime() - clientSession.getLastAccessedTime();
            assertTrue (diff < 10000);
        } catch (Exception ex) {
            fail("Unexpected exception " + ex);
        }
    }