@TestTargetNew(
        level = TestLevel.PARTIAL_COMPLETE,
        notes = "",
        method = "valueOf",
        args = {java.lang.String.class}
    )
    public void testValueOf() {
        String SQL_NOTVALID1 = "ABCDEF"; // Invalid date string
        String SQL_NOTVALID2 = "12321.43.56"; // Invalid date string
        String SQL_NOTVALID3 = null; // Invalid date string
        String[] SQL_INVALIDARRAY = { SQL_NOTVALID1, SQL_NOTVALID2,
                SQL_NOTVALID3 };

        Date theDate;

        for (String element : SQL_DATEARRAY) {
            theDate = Date.valueOf(element);
            assertEquals(element, theDate.toString());
        } // end for

        for (String element : SQL_INVALIDARRAY) {
            try {
                theDate = Date.valueOf(element);
                fail("Should throw IllegalArgumentException.");
            } catch (IllegalArgumentException e) {
                //expected
            } // end try
        } // end for

    }