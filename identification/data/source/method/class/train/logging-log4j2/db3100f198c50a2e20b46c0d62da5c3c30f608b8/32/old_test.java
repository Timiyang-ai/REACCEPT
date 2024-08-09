    protected SyslogAppender createAppender(final String protocol, final String format) {
        return newSyslogAppenderBuilder(protocol, format, includeNewLine).build();
    }