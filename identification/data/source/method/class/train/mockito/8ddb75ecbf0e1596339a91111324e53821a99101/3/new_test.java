    @Test
    public void contains_matcher() {
        when(mock.oneArg(contains("ell"))).thenReturn("1");
        when(mock.oneArg(contains("ld"))).thenReturn("2");

        assertEquals("1", mock.oneArg("hello"));
        assertEquals("2", mock.oneArg("world"));
        assertEquals(null, mock.oneArg("xlx"));
    }