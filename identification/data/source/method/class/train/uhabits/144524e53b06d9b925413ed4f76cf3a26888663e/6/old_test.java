    @Test
    public void test_getAllValues_moveBackwardsInTime()
    {
        travelInTime(-3);

        int[] expectedValues = {
            CHECKED_EXPLICITLY,
            CHECKED_EXPLICITLY,
            CHECKED_EXPLICITLY,
            CHECKED_IMPLICITLY,
            CHECKED_IMPLICITLY,
            CHECKED_EXPLICITLY,
            CHECKED_EXPLICITLY
        };

        int[] actualValues = nonDailyHabit.getCheckmarks().getAllValues();

        assertThat(actualValues, equalTo(expectedValues));
    }