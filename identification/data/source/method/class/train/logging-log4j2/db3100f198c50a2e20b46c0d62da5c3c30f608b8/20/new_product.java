@PluginFactory
    public static SyslogAppender createAppender(
            // @formatter:off
            @PluginAttribute("host") final String host,
            @PluginAttribute(value = "port", defaultInt = 0) final int port,
            @PluginAttribute("protocol") final String protocolStr,
            @PluginElement("SSL") final SslConfiguration sslConfig,
            @PluginAttribute(value = "connectTimeoutMillis", defaultInt = 0) final int connectTimeoutMillis,
            @PluginAliases("reconnectionDelay") // deprecated
            @PluginAttribute(value = "reconnectionDelayMillis", defaultInt = 0) final int reconnectionDelayMillis,
            @PluginAttribute(value = "immediateFail", defaultBoolean = true) final boolean immediateFail,
            @PluginAttribute("name") final String name,
            @PluginAttribute(value = "immediateFlush", defaultBoolean = true) final boolean immediateFlush,
            @PluginAttribute(value = "ignoreExceptions", defaultBoolean = true) final boolean ignoreExceptions,
            @PluginAttribute(value = "facility", defaultString = "LOCAL0") final Facility facility,
            @PluginAttribute("id") final String id,
            @PluginAttribute(value = "enterpriseNumber", defaultInt = Rfc5424Layout.DEFAULT_ENTERPRISE_NUMBER) final int enterpriseNumber,
            @PluginAttribute(value = "includeMdc", defaultBoolean = true) final boolean includeMdc,
            @PluginAttribute("mdcId") final String mdcId,
            @PluginAttribute("mdcPrefix") final String mdcPrefix,
            @PluginAttribute("eventPrefix") final String eventPrefix,
            @PluginAttribute(value = "newLine") final boolean newLine,
            @PluginAttribute("newLineEscape") final String escapeNL,
            @PluginAttribute("appName") final String appName,
            @PluginAttribute("messageId") final String msgId,
            @PluginAttribute("mdcExcludes") final String excludes,
            @PluginAttribute("mdcIncludes") final String includes,
            @PluginAttribute("mdcRequired") final String required,
            @PluginAttribute("format") final String format,
            @PluginElement("Filter") final Filter filter,
            @PluginConfiguration final Configuration config,
            @PluginAttribute(value = "charset", defaultString = "UTF-8") final Charset charsetName,
            @PluginAttribute("exceptionPattern") final String exceptionPattern,
            @PluginElement("LoggerFields") final LoggerFields[] loggerFields, 
            @PluginAttribute(value = "advertise") final boolean advertise) {
        // @formatter:on

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
        final AbstractSocketManager manager = createSocketManager(name, protocol, host, port, connectTimeoutMillis,
                sslConfig, reconnectionDelayMillis, immediateFail, layout, Constants.ENCODER_BYTE_BUFFER_SIZE);

        return new SyslogAppender(name, layout, filter, ignoreExceptions, immediateFlush, manager,
                advertise ? config.getAdvertiser() : null);
    }