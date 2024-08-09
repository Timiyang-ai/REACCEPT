@Test
    public void testTrace() {
        ListAppender appender = new ListAppender("List");
        appender.start();
        Logger root = Logger.getRootLogger();
        root.getLogger().addAppender(appender);
        root.setLevel(Level.INFO);

        Logger tracer = Logger.getLogger("com.example.Tracer");
        tracer.setLevel(Level.TRACE);

        tracer.trace("Message 1");
        root.trace("Discarded Message");
        root.trace("Discarded Message");

        List<LogEvent> msgs = appender.getEvents();
        assertEquals(1, msgs.size());
        LogEvent event = (LogEvent) msgs.get(0);
        assertEquals(org.apache.logging.log4j.Level.TRACE, event.getLevel());
        assertEquals("Message 1", event.getMessage().getFormat());
        appender.stop();
        root.getLogger().removeAppender(appender);
    }