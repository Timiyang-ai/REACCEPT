    public void test_toString_us() throws Exception {
        // Ensure that no matter where this is run, we know what time zone to expect.
        Locale.setDefault(Locale.US);
        TimeZone.setDefault(TimeZone.getTimeZone("America/Chicago"));
        assertEquals("Wed Dec 31 18:00:00 CST 1969", new Date(0).toString());
    }