    @Test
    void previous() {
        assertThrows(NoSuchElementException.class, State.CREATED::previous);
        assertEquals(State.CREATED, State.INITIALIZED.previous());
        assertEquals(State.INITIALIZED, State.RESOLVED.previous());
        assertEquals(State.RESOLVED, State.STARTED.previous());
        assertEquals(State.STARTED, State.STOPPED.previous());
    }