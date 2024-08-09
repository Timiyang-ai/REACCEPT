@PluginFactory
    public static SyslogAppender createAppender(
            // @formatter:off
            @PluginAttribute("host") final String host,
            @PluginAttribute("port") final String portNum,
            @PluginAttribute("protocol") final String protocolStr,
            @PluginElement("SSL") final SslConfiguration sslConfig,
            @PluginAttribute("reconnectionDelay") final String delay,
            @PluginAttribute("immediateFail") final String immediateFail,
            @PluginAttribute("name") final String name,
            @PluginAttribute("immediateFlush") final String immediateFlush,
            @PluginAttribute("ignoreExceptions") final String ignore,
            @PluginAttribute("facility") final String facility,
            @PluginAttribute("id") final String id,
            @PluginAttribute("enterpriseNumber") final String ein,
            @PluginAttribute("includeMdc") final String includeMdc,
            @PluginAttribute("mdcId") final String mdcId,
            @PluginAttribute("mdcPrefix") final String mdcPrefix,
            @PluginAttribute("eventPrefix") final String eventPrefix,
            @PluginAttribute("newLine") final String includeNL,
            @PluginAttribute("newLineEscape") final String escapeNL,
            @PluginAttribute("appName") final String appName,
            @PluginAttribute("messageId") final String msgId,
            @PluginAttribute("mdcExcludes") final String excludes,
            @PluginAttribute("mdcIncludes") final String includes,
            @PluginAttribute("mdcRequired") final String required,
            @PluginAttribute("format") final String format,
            @PluginElement("Filters") final Filter filter,
            @PluginConfiguration final Configuration config,
            @PluginAttribute("charset") final String charsetName,
            @PluginAttribute("exceptionPattern") final String exceptionPattern,
            @PluginElement("LoggerFields") final LoggerFields[] loggerFields, @PluginAttribute("advertise") final String advertise) {
        // @formatter:on

        final boolean isFlush = Booleans.parseBoolean(immediateFlush, true);
        final boolean ignoreExceptions = Booleans.parseBoolean(ignore, true);
        final int reconnectDelay = AbstractAppender.parseInt(delay, 0);
        final boolean fail = Booleans.parseBoolean(immediateFail, true);
        final int port = AbstractAppender.parseInt(portNum, 0);
        final boolean isAdvertise = Boolean.parseBoolean(advertise);
        final Protocol protocol = EnglishEnums.valueOf(Protocol.class, protocolStr);
        final String useTlsMessageFormat = Boolean.toString(sslConfig != null || protocol == Protocol.SSL);
        final Layout<? extends Serializable> layout = RFC5424.equalsIgnoreCase(format) ?
            Rfc5424Layout.createLayout(facility, id, ein, includeMdc, mdcId, mdcPrefix, eventPrefix, includeNL,
                escapeNL, appName, msgId, excludes, includes, required, exceptionPattern, useTlsMessageFormat, loggerFields,
                config) :
            SyslogLayout.createLayout(facility, includeNL, escapeNL, charsetName);

        if (name == null) {
            LOGGER.error("No name provided for SyslogAppender");
            return null;
        }
        final AbstractSocketManager manager = createSocketManager(name, protocol, host, port, sslConfig, reconnectDelay, fail, layout);

        return new SyslogAppender(name, layout, filter, ignoreExceptions, isFlush, manager,
                isAdvertise ? config.getAdvertiser() : null);
    }