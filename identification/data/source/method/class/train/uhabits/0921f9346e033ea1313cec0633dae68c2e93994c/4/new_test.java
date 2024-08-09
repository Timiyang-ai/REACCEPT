    @Test
    public void test_toggle()
    {
        assertTrue(reps.containsTimestamp(today));
        reps.toggle(today);
        assertFalse(reps.containsTimestamp(today));
        verify(listener).onModelChange();
        reset(listener);

        assertFalse(reps.containsTimestamp(today.minus(1)));
        reps.toggle(today.minus(1));
        assertTrue(reps.containsTimestamp(today.minus(1)));
        verify(listener).onModelChange();
        reset(listener);

        habit.setType(Habit.NUMBER_HABIT);
        reps.toggle(today, 100);
        Repetition check = reps.getByTimestamp(today);
        assertNotNull(check);
        assertThat(check.getValue(), equalTo(100));
        verify(listener).onModelChange();
        reset(listener);

        reps.toggle(today, 500);
        check = reps.getByTimestamp(today);
        assertNotNull(check);
        assertThat(check.getValue(), equalTo(500));
        verify(listener, times(2)).onModelChange();
        reset(listener);
    }