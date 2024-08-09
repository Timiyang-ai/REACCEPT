@TestTargetNew(
        level = TestLevel.COMPLETE,
        notes = "",
        method = "setHours",
        args = {int.class}
    )
    @SuppressWarnings("deprecation")
    public void testSetHours() {
        Date theDate = new Date(TIME_TESTDATE1);
        try {
            theDate.setHours(22);
            fail("Should throw IllegalArgumentException.");
        } catch (IllegalArgumentException ie) {
            //expected
        } // end try
    }