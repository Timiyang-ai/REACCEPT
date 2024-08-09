    @Test
    public void test_getByInterval_withNumericalHabits() throws Exception
    {
        CheckmarkList checkmarks = numericalHabit.getCheckmarks();

        List<Checkmark> expected =
            Arrays.asList(new Checkmark(day(1), 200), new Checkmark(day(2), 0),
                new Checkmark(day(3), 300), new Checkmark(day(4), 0),
                new Checkmark(day(5), 400));

        List<Checkmark> actual = checkmarks.getByInterval(day(5), day(1));
        assertThat(actual, equalTo(expected));
    }