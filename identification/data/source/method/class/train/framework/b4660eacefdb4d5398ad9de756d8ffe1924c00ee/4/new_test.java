    @Test
    public void containsId_unknownObject() throws SQLException {

        Handler ensureNoLogging = new Handler() {

            @Override
            public void publish(LogRecord record) {
                fail("No messages should be logged");

            }

            @Override
            public void flush() {
            }

            @Override
            public void close() throws SecurityException {
            }
        };

        SQLContainer container = new SQLContainer(new FreeformQuery(
                "SELECT * FROM people", connectionPool, "ID"));
        Logger logger = Logger.getLogger(SQLContainer.class.getName());

        logger.addHandler(ensureNoLogging);
        try {
            assertFalse(container.containsId(new Object()));
        } finally {
            logger.removeHandler(ensureNoLogging);
        }
    }