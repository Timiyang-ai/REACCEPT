    public void test_parse_timezones() {
       assertEquals(
               Date.parse("Wed, 06 Jan 2016 11:55:59 GMT+05:00"),
               Date.parse("Wed, 06 Jan 2016 11:55:59 GMT+0500"));

        assertEquals(
                Date.parse("Wed, 06 Jan 2016 11:55:59 GMT+05:00"),
                Date.parse("Wed, 06 Jan 2016 11:55:59 GMT+05"));

    }