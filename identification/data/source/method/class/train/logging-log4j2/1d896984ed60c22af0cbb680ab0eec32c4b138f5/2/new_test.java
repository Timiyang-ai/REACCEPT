    private SyslogAppender createAppender() {
        String format;

        if (messageFormat == TlsSyslogMessageFormat.LEGACY_BSD) {
            format = "LEGACY_BSD";
        } else {
            format = "RFC5424";
        }
        final SslConfiguration sslConfiguration1 = sslConfiguration;
        final boolean newLine = includeNewLine;
        final String format1 = format;

        // @formatter:off
        return SyslogAppender.newSyslogAppenderBuilder()
                .setHost("localhost")
                .setPort(PORTNUM)
                .setProtocol(EnglishEnums.valueOf(Protocol.class, "SSL"))
                .setSslConfiguration(sslConfiguration1)
                .setConnectTimeoutMillis(0)
                .setReconnectDelayMillis(-1)
                .setImmediateFail(true)
                .setName("TestApp")
                .setImmediateFlush(true)
                .setIgnoreExceptions(false).setFilter(null)
                .setConfiguration(null)
                .setAdvertise(false)
                .setFacility(Facility.LOCAL0)
                .setId("Audit")
                .setEnterpriseNumber(18060)
                .setIncludeMdc(true)
                .setMdcId("RequestContext")
                .setMdcPrefix(null)
                .setEventPrefix(null)
                .setNewLine(newLine)
                .setAppName("TestApp")
                .setMsgId("Test")
                .setExcludes(null)
                .setIncludeMdc(true)
                .setRequired(null)
                .setFormat(format1)
                .setCharsetName(null)
                .setExceptionPattern(null)
                .setLoggerFields(null)
                .build();
        // @formatter:on
    }