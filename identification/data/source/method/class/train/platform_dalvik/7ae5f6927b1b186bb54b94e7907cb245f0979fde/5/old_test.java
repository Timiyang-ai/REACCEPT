@TestTargetNew(
        level = TestLevel.COMPLETE,
        notes = "",
        method = "setMinutes",
        args = {int.class}
    )
    @SuppressWarnings("deprecation")
    public void testSetMinutes() {
        Date theDate = new Date(TIME_TESTDATE1);
        try {
            theDate.setMinutes(54);
            fail("Should throw IllegalArgumentException.");
        } catch (IllegalArgumentException ie) {
            //expected
        } // end try

    }