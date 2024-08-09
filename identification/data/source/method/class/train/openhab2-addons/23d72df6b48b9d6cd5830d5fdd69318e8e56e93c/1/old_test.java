    @SuppressWarnings("deprecation")
    @Test
    public void resolveDateTimeTest() {
        int date = Utils.fromHex("858B"); // 05-09-2011
        int time = Utils.fromHex("2E"); // 23:00

        Date result = Utils.resolveDateTime(date, time);

        assertEquals(5, result.getDate());
        assertEquals(9, result.getMonth());
        assertEquals(2011, result.getYear());
        assertEquals(23, result.getHours());
        assertEquals(00, result.getMinutes());
    }