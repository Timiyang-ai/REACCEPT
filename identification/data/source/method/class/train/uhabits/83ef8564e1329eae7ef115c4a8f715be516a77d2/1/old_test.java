    @Test
    public void test_getTodayValue()
    {
        toggleRepetitions(0, 20);
        double actual = habit.getScores().getTodayValue();
        assertThat(actual, closeTo(0.655747, E));
    }