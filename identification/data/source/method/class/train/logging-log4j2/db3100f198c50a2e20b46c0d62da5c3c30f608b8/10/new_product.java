@Deprecated
    public static SyslogAppender createAppender(
            // @formatter:off
            final String host,
            final int port,
            final String protocolStr,
            final SslConfiguration sslConfiguration,
            final int connectTimeoutMillis,
            final int reconnectDelayMillis,
            final boolean immediateFail,
            final String name,
            final boolean immediateFlush,
            final boolean ignoreExceptions,
            final Facility facility,
            final String id,
            final int enterpriseNumber,
            final boolean includeMdc,
            final String mdcId,
            final String mdcPrefix,
            final String eventPrefix,
            final boolean newLine,
            final String escapeNL,
            final String appName,
            final String msgId,
            final String excludes,
            final String includes,
            final String required,
            final String format,
            final Filter filter,
            final Configuration configuration,
            final Charset charset,
            final String exceptionPattern,
            final LoggerFields[] loggerFields, 
            final boolean advertise) {
        // @formatter:on

        // @formatter:off
        return newSyslogAppenderBuilder()
                .withHost(host)
                .withPort(port)
                .withProtocol(EnglishEnums.valueOf(Protocol.class, protocolStr))
                .withSslConfiguration(sslConfiguration)
                .withConnectTimeoutMillis(connectTimeoutMillis)
                .withReconnectDelayMillis(reconnectDelayMillis)
                .withImmediateFail(immediateFail)
                .withName(appName)
                .withImmediateFlush(immediateFlush)
                .withIgnoreExceptions(ignoreExceptions)
                .withFilter(filter)
                .setConfiguration(configuration)
                .withAdvertise(advertise)
                .setFacility(facility)
                .setId(id)
                .setEnterpriseNumber(enterpriseNumber)
                .setIncludeMdc(includeMdc)
                .setMdcId(mdcId)
                .setMdcPrefix(mdcPrefix)
                .setEventPrefix(eventPrefix)
                .setNewLine(newLine)
                .setAppName(appName)
                .setMsgId(msgId)
                .setExcludes(excludes)
                .setIncludeMdc(includeMdc)
                .setRequired(required)
                .setFormat(format)
                .setCharsetName(charset)
                .setExceptionPattern(exceptionPattern)
                .setLoggerFields(loggerFields)
                .build();
        // @formatter:on
    }