    @Test
    public void test_invalidateNewerThan()
    {
        assertThat(habit.getScores().getTodayValue(), closeTo(0.0, E));

        toggleRepetitions(0, 2);
        assertThat(habit.getScores().getTodayValue(), closeTo(0.101149, E));

        habit.setFrequency(new Frequency(1, 2));
        habit.getScores().invalidateNewerThan(new Timestamp(0));

        assertThat(habit.getScores().getTodayValue(), closeTo(0.051922, E));
    }