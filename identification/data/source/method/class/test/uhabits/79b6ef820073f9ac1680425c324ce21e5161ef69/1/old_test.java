    @Test
    public void test_getTodayValue()
    {
        CheckmarkList checkmarks = nonDailyHabit.getCheckmarks();

        travelInTime(-1);
        assertThat(checkmarks.getTodayValue(), equalTo(UNCHECKED));

        travelInTime(0);
        assertThat(checkmarks.getTodayValue(), equalTo(CHECKED_EXPLICITLY));

        travelInTime(1);
        assertThat(checkmarks.getTodayValue(), equalTo(UNCHECKED));
    }