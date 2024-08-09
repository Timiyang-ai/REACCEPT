@Test
    public void test_chrono_byName() {
        Chronology<ISOChronology> c = ISOChronology.INSTANCE;
        Chronology<?> test = Chronology.of("ISO");
        Assert.assertNotNull(test, "The ISO calendar could not be found byName");
        Assert.assertEquals(test.getId(), "ISO", "ID mismatch");
        Assert.assertEquals(test.getCalendarType(), "iso8601", "Type mismatch");
        Assert.assertEquals(test, c);
    }