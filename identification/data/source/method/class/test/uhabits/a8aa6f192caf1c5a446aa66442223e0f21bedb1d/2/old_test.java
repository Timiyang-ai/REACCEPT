    @Test
    public void pop() throws Exception
    {
        when(prefs.getLastHintNumber()).thenReturn(-1);
        assertThat(hintList.pop(), equalTo("hint1"));
        verify(prefs).updateLastHint(0, today);

        when(prefs.getLastHintNumber()).thenReturn(2);
        assertNull(hintList.pop());
    }