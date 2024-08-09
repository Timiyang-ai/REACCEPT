@TestTargetNew(
        level = TestLevel.COMPLETE,
        notes = "",
        method = "toString",
        args = {}
    )
    public void testToString() {
		// Loop through the timezones testing the String conversion for each
		for (int i = 0; i < TIMEZONES.length; i++) {
			testToString(TIMEZONES[i], TIME_ARRAY, SQL_TZ_DATEARRAYS[i]);
        } // end for

    }