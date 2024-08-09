    @Test
    public void test_getAll()
    {
        toggleRepetitions(0, 20);

        double expectedValues[] = {
            0.655747,
            0.636894,
            0.617008,
            0.596033,
            0.573910,
            0.550574,
            0.525961,
            0.500000,
            0.472617,
            0.443734,
            0.413270,
            0.381137,
            0.347244,
            0.311495,
            0.273788,
            0.234017,
            0.192067,
            0.147820,
            0.101149,
            0.051922,
        };

        int i = 0;
        for (Score s : habit.getScores())
            assertThat(s.getValue(), closeTo(expectedValues[i++], E));
    }