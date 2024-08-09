@Test
    public void testIsTraceEnabled() {
        ListAppender appender = new ListAppender("List");
        appender.start();
        Logger root = Logger.getRootLogger();
        root.getLogger().addAppender(appender);
        root.setLevel(Level.INFO);

        Logger tracer = Logger.getLogger("com.example.Tracer");
        tracer.setLevel(Level.TRACE);

        assertTrue(tracer.isTraceEnabled());
        assertFalse(root.isTraceEnabled());
        appender.stop();
        root.getLogger().removeAppender(appender);
    }