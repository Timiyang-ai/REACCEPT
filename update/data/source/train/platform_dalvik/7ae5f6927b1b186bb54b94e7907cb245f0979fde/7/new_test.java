@TestTargetNew(
        level = TestLevel.COMPLETE,
        notes = "",
        method = "getSeconds",
        args = {}
    )
    @SuppressWarnings("deprecation")
    public void testGetSeconds() {
        Date theDate = new Date(TIME_TESTDATE1);
        try {
            theDate.getSeconds();
            fail("Should throw IllegalArgumentException.");
        } catch (IllegalArgumentException ie) {
            //expected
        } // end try
    }