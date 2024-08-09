@TestTargetNew(
        level = TestLevel.COMPLETE,
        notes = "",
        method = "toString",
        args = {}
    )
    public void testToString() {
        // This test is set up for GMT time zone, so need to set the time zone
        // to GMT first
        TimeZone.setDefault(TimeZone.getTimeZone("GMT"));

        for (int i = 0; i < TIME_ARRAY.length; i++) {
            Date theDate = new Date(TIME_ARRAY[i]);
            assertEquals(SQL_DATEARRAY[i], theDate.toString());
        } // end for

    }