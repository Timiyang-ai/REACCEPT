    @Test
    public void close() {
        final StartStop startStop = new StartStop(arg -> "foo", arg -> null);
        startStop.close();
    }