public void factory_ofInstantUTC() {
        Instant instant = Instant.ofEpochSecond(86400 + 5 * 3600 + 10 * 60 + 20);
        OffsetDateTime test = OffsetDateTime.ofInstantUTC(instant);
        check(test, 1970, 1, 2, 5, 10, 20, 0, ZoneOffset.UTC);
    }