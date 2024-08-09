@PluginFactory
    public static SyslogAppender createAppender(
            // @formatter:off
            @PluginAttribute("host") final String host,
            @PluginAttribute(value = "port", defaultIntValue = 0) final int port,
            @PluginAttribute("protocol") final String protocolStr,
            @PluginElement("SSL") final SslConfiguration sslConfig,
            @PluginAttribute(value = "reconnectionDelay", defaultIntValue = 0) final int reconnectionDelay,
            @PluginAttribute(value = "immediateFail", defaultBooleanValue = true) final boolean immediateFail,
            @PluginAttribute("name") final String name,
            @PluginAttribute(value = "immediateFlush", defaultBooleanValue = true) final boolean immediateFlush,
            @PluginAttribute(value = "ignoreExceptions", defaultBooleanValue = true) final boolean ignoreExceptions,
            @PluginAttribute(value = "facility", defaultStringValue = "LOCAL0") final Facility facility,
            @PluginAttribute("id") final String id,
            @PluginAttribute(value = "enterpriseNumber", defaultIntValue = Rfc5424Layout.DEFAULT_ENTERPRISE_NUMBER) final int enterpriseNumber,
            @PluginAttribute(value = "includeMdc", defaultBooleanValue = true) final boolean includeMdc,
            @PluginAttribute("mdcId") final String mdcId,
            @PluginAttribute("mdcPrefix") final String mdcPrefix,
            @PluginAttribute("eventPrefix") final String eventPrefix,
            @PluginAttribute(value = "newLine", defaultBooleanValue = false) final boolean newLine,
            @PluginAttribute("newLineEscape") final String escapeNL,
            @PluginAttribute("appName") final String appName,
            @PluginAttribute("messageId") final String msgId,
            @PluginAttribute("mdcExcludes") final String excludes,
            @PluginAttribute("mdcIncludes") final String includes,
            @PluginAttribute("mdcRequired") final String required,
            @PluginAttribute("format") final String format,
            @PluginElement("Filters") final Filter filter,
            @PluginConfiguration final Configuration config,
            @PluginAttribute(value = "charset", defaultStringValue = "UTF-8") final Charset charsetName,
            @PluginAttribute("exceptionPattern") final String exceptionPattern,
            @PluginElement("LoggerFields") final LoggerFields[] loggerFields,
            @PluginAttribute(value = "advertise", defaultBooleanValue = false) final boolean advertise) {
        // @formatter:on

        // TODO: add Protocol to TypeConverters
        final Protocol protocol = EnglishEnums.valueOf(Protocol.class, protocolStr);
        final boolean useTlsMessageFormat = sslConfig != null || protocol == Protocol.SSL;
        final Layout<? extends Serializable> layout = RFC5424.equalsIgnoreCase(format) ?
            Rfc5424Layout.createLayout(facility, id, enterpriseNumber, includeMdc, mdcId, mdcPrefix, eventPrefix, newLine,
                escapeNL, appName, msgId, excludes, includes, required, exceptionPattern, useTlsMessageFormat, loggerFields,
                config) :
            SyslogLayout.createLayout(facility, newLine, escapeNL, charsetName);

        if (name == null) {
            LOGGER.error("No name provided for SyslogAppender");
            return null;
        }
        final AbstractSocketManager manager = createSocketManager(name, protocol, host, port, sslConfig,
            reconnectionDelay, immediateFail, layout);

        return new SyslogAppender(name, layout, filter, ignoreExceptions, immediateFlush, manager,
                advertise ? config.getAdvertiser() : null);
    }