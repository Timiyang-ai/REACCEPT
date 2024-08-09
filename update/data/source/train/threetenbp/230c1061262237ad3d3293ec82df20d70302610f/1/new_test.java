@Test(groups={"tck"})
    public void factoryUTC_ofInstant() {
        Instant instant = Instant.ofEpochSecond(86400 + 5 * 3600 + 10 * 60 + 20);
        ZonedDateTime test = ZonedDateTime.ofInstantUTC(instant);
        check(test, 1970, 1, 2, 5, 10, 20, 0, ZoneOffset.UTC, ZoneId.UTC);
    }