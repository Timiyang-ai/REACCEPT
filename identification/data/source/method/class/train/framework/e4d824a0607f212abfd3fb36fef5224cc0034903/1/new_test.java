    @Test
    public void propertyIdToHumanFriendly() {
        assertEquals("", SharedUtil.propertyIdToHumanFriendly(""));
        assertEquals("First Name",
                SharedUtil.propertyIdToHumanFriendly("firstName"));
        assertEquals("First Name",
                SharedUtil.propertyIdToHumanFriendly("FirstName"));
        assertEquals("First Name",
                SharedUtil.propertyIdToHumanFriendly("FIRST_NAME"));
        assertEquals("Firstname",
                SharedUtil.propertyIdToHumanFriendly("FIRSTNAME"));

        assertEquals("2015 Q3",
                SharedUtil.propertyIdToHumanFriendly("2015_Q3"));
        assertEquals("Column X",
                SharedUtil.propertyIdToHumanFriendly("_COLUMN_X"));
        assertEquals("Column X",
                SharedUtil.propertyIdToHumanFriendly("__COLUMN_X"));
        assertEquals("1column Foobar",
                SharedUtil.propertyIdToHumanFriendly("1COLUMN_FOOBAR"));
        assertEquals("Result 2015",
                SharedUtil.propertyIdToHumanFriendly("RESULT_2015"));
        assertEquals("2015result",
                SharedUtil.propertyIdToHumanFriendly("2015RESULT"));
        assertEquals("Result2015",
                SharedUtil.propertyIdToHumanFriendly("RESULT2015"));

    }