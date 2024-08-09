@TestTargetNew(
        level = TestLevel.COMPLETE,
        notes = "",
        method = "getHours",
        args = {}
    )
    @SuppressWarnings("deprecation")
    public void testGetHours() {
        Date theDate = new Date(TIME_TESTDATE1);
        try {
            theDate.getHours();
            fail("Should throw IllegalArgumentException.");
        } catch (IllegalArgumentException ie) {
            //expected
        } // end try
    }