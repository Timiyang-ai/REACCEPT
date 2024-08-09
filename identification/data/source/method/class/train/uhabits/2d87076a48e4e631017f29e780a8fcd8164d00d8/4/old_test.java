    @Test
    public void test_getValues()
    {
        toggleRepetitions(0, 20);

        Timestamp today = DateUtils.getToday();
        Timestamp from = today.minus(4);
        Timestamp to = today.minus(2);

        double[] expected = {
            0.617008, 0.596033, 0.573909,
        };

        double[] actual = habit.getScores().getValues(from, to);
        assertThat(actual.length, equalTo(expected.length));

        for (int i = 0; i < actual.length; i++)
            assertThat(actual[i], closeTo(expected[i], E));
    }