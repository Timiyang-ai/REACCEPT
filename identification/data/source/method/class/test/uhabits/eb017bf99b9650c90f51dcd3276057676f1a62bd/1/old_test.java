    @Test
    public void test_getOldest()
    {
        Repetition rep = reps.getOldest();
        assertThat(rep.getTimestamp(), equalTo(today.minus(7)));
    }