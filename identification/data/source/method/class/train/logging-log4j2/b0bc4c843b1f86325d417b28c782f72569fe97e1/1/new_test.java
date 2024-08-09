    protected Builder newSyslogAppenderBuilder(final String protocol, final String format, final boolean newLine) {
        // @formatter:off
        return SyslogAppender.newSyslogAppenderBuilder()
                .setPort(PORTNUM)
                .setProtocol(EnglishEnums.valueOf(Protocol.class, protocol))
                .setReconnectDelayMillis(-1)
                .setName("TestApp")
                .setIgnoreExceptions(false)
                .setId("Audit")
                .setEnterpriseNumber(18060)
                .setMdcId("RequestContext")
                .setNewLine(newLine)
                .setAppName("TestApp")
                .setMsgId("Test")
                .setFormat(format);
        // @formatter:on
    }