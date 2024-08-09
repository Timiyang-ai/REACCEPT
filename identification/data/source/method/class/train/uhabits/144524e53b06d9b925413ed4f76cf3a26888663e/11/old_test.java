    @Test
    public void test_getValues_withInvalidInterval()
    {
        int values[] = nonDailyHabit
            .getCheckmarks()
            .getValues(new Timestamp(0L).plus(100), new Timestamp(0L));
        assertThat(values, equalTo(new int[0]));
    }