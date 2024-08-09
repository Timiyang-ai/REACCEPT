    @Test
    public void shouldShow() throws Exception
    {
        when(prefs.getLastHintTimestamp()).thenReturn(today);
        assertFalse(hintList.shouldShow());

        when(prefs.getLastHintTimestamp()).thenReturn(yesterday);
        assertTrue(hintList.shouldShow());
    }