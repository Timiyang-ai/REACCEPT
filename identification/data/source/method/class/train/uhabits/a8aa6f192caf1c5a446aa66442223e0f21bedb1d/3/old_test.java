    @Test
    public void test_snapIntervalsTogether_1() throws Exception
    {
        ArrayList<CheckmarkList.Interval> original = new ArrayList<>();
        original.add(new CheckmarkList.Interval(day(40), day(40), day(34)));
        original.add(new CheckmarkList.Interval(day(25), day(25), day(19)));
        original.add(new CheckmarkList.Interval(day(16), day(16), day(10)));
        original.add(new CheckmarkList.Interval(day(8), day(8), day(2)));

        ArrayList<CheckmarkList.Interval> expected = new ArrayList<>();
        expected.add(new CheckmarkList.Interval(day(40), day(40), day(34)));
        expected.add(new CheckmarkList.Interval(day(25), day(25), day(19)));
        expected.add(new CheckmarkList.Interval(day(18), day(16), day(12)));
        expected.add(new CheckmarkList.Interval(day(11), day(8), day(5)));

        CheckmarkList.snapIntervalsTogether(original);
        assertThat(original, equalTo(expected));
    }