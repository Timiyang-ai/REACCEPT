    @Test
    public void test_compute_withDailyHabit()
    {
        int check = 1;
        double freq = 1.0;
        assertThat(compute(freq, 0, check), closeTo(0.051922, E));
        assertThat(compute(freq, 0.5, check), closeTo(0.525961, E));
        assertThat(compute(freq, 0.75, check), closeTo(0.762981, E));

        check = 0;
        assertThat(compute(freq, 0, check), closeTo(0, E));
        assertThat(compute(freq, 0.5, check), closeTo(0.474039, E));
        assertThat(compute(freq, 0.75, check), closeTo(0.711058, E));
    }