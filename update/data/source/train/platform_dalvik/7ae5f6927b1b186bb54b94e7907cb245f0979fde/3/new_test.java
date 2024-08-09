@TestTargetNew(
        level = TestLevel.COMPLETE,
        notes = "",
        method = "getMinutes",
        args = {}
    )
    @SuppressWarnings("deprecation")
    public void testGetMinutes() {
        Date theDate = new Date(TIME_TESTDATE1);
        try {
            theDate.getMinutes();
            fail("Should throw IllegalArgumentException.");
        } catch (IllegalArgumentException ie) {
            //expected
        } // end try
    }