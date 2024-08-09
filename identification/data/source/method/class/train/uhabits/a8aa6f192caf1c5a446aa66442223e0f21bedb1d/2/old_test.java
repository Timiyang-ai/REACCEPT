    @Test
    public void test_buildIntervals_1() throws Exception
    {
        Repetition reps[] = new Repetition[]{
            new Repetition(day(23), CHECKED_EXPLICITLY),
            new Repetition(day(18), CHECKED_EXPLICITLY),
            new Repetition(day(8), CHECKED_EXPLICITLY),
        };

        ArrayList<CheckmarkList.Interval> expected = new ArrayList<>();
        expected.add(new CheckmarkList.Interval(day(23), day(23), day(17)));
        expected.add(new CheckmarkList.Interval(day(18), day(18), day(12)));
        expected.add(new CheckmarkList.Interval(day(8), day(8), day(2)));

        ArrayList<CheckmarkList.Interval> actual;
        actual = CheckmarkList.buildIntervals(Frequency.WEEKLY, reps);
        assertThat(actual, equalTo(expected));
    }