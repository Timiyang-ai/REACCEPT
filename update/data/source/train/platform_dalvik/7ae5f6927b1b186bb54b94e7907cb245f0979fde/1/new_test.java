@TestTargetNew(
        level = TestLevel.COMPLETE,
        notes = "",
        method = "setSeconds",
        args = {int.class}
    )
    @SuppressWarnings("deprecation")
    public void testSetSeconds() {
        Date theDate = new Date(TIME_TESTDATE1);
        try {
            theDate.setSeconds(36);
            fail("Should throw IllegalArgumentException.");
        } catch (IllegalArgumentException ie) {
            //expected
        } // end try
    }