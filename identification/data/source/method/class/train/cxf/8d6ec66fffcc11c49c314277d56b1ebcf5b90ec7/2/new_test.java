    @Test
    public void logProtocolHeadersTest() {
        Map<String, List<Object>> headerMap = new HashMap<>();
        headerMap.put("Normal-Header", Arrays.asList("normal"));
        headerMap.put("Multivalue-Header", Arrays.asList("first", "second"));
        headerMap.put("Authorization", Arrays.asList("myPassword"));
        headerMap.put("Null-Header", Arrays.asList((String)null));

        //Set up test logger
        Logger logger = Logger.getAnonymousLogger();
        // remove all "normal" handlers and just use our custom handler for testing
        logger.setUseParentHandlers(false);
        logger.setLevel(Level.INFO);
        for (Handler h : logger.getHandlers()) {
            logger.removeHandler(h);
        }
        logger.addHandler(new Handler() {

            @Override
            public void publish(LogRecord record) {
                String msg = record.getMessage();
                if (msg.startsWith("Normal-Header")) {
                    assertTrue("Unexpected output for normal header - expected Normal-Header: normal, received " + msg,
                               "Normal-Header: normal".equals(msg));
                } else if (msg.startsWith("Multivalue-Header")) {
                    assertTrue("Unexpected output for multi-value header - expected Multivalue-Header: first or "
                        + "Multivalue-Header: second, received: " + msg,
                        "Multivalue-Header: first".equals(msg) || "Multivalue-Header: second".equals(msg));
                } else if (msg.startsWith("Authorization")) {
                    assertTrue("Unexpected output for sensitive header - expected Authorization: ***, received " + msg,
                               "Authorization: ***".equals(msg));
                } else if (msg.startsWith("Null-Header")) {
                    assertTrue("Unexpected output for null header - expected Null-Header: <null>, received " + msg,
                               "Null-Header: <null>".equals(msg));
                } else {
                    fail("Unexpected header logged: " + msg);
                }

            }

            @Override
            public void flush() {
                // no-op
            }

            @Override
            public void close() throws SecurityException {
                // no-op
            } });

        Headers.logProtocolHeaders(logger, Level.INFO, headerMap, false);
    }