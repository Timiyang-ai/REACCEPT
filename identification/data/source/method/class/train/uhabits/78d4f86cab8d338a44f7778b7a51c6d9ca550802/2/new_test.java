    @Test
    public void test_contains()
    {
        assertTrue(reps.containsTimestamp(today));
        assertTrue(reps.containsTimestamp(today.minus(2)));
        assertTrue(reps.containsTimestamp(today.minus(3)));

        assertFalse(reps.containsTimestamp(today.minus(1)));
        assertFalse(reps.containsTimestamp(today.minus(4)));
    }