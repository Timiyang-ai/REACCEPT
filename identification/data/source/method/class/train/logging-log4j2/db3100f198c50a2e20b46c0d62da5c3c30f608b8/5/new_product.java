@PluginFactory
    public static SyslogAppender createAppender(
            // @formatter:off
            @PluginAttribute("host") final String host,
            @PluginAttribute("port") @PluginDefault("0") final int port,
            @PluginAttribute("protocol") final String protocolStr,
            @PluginElement("SSL") final SslConfiguration sslConfig,
            @PluginAttribute("reconnectionDelay") @PluginDefault("0") final int reconnectionDelay,
            @PluginAttribute("immediateFail") @PluginDefault("true") final boolean immediateFail,
            @PluginAttribute("name") final String name,
            @PluginAttribute("immediateFlush") @PluginDefault("true") final boolean immediateFlush,
            @PluginAttribute("ignoreExceptions") @PluginDefault("true") final boolean ignoreExceptions,
            @PluginAttribute("facility") @PluginDefault("LOCAL0") final Facility facility,
            @PluginAttribute("id") final String id,
            @PluginAttribute("enterpriseNumber") @PluginDefault(Rfc5424Layout.DEFAULT_ENTERPRISE_NUMBER) final int enterpriseNumber,
            @PluginAttribute("includeMdc") @PluginDefault("true") final boolean includeMdc,
            @PluginAttribute("mdcId") final String mdcId,
            @PluginAttribute("mdcPrefix") final String mdcPrefix,
            @PluginAttribute("eventPrefix") final String eventPrefix,
            @PluginAttribute("newLine") @PluginDefault("false") final boolean newLine,
            @PluginAttribute("newLineEscape") final String escapeNL,
            @PluginAttribute("appName") final String appName,
            @PluginAttribute("messageId") final String msgId,
            @PluginAttribute("mdcExcludes") final String excludes,
            @PluginAttribute("mdcIncludes") final String includes,
            @PluginAttribute("mdcRequired") final String required,
            @PluginAttribute("format") final String format,
            @PluginElement("Filters") final Filter filter,
            @PluginConfiguration final Configuration config,
            @PluginAttribute("charset") @PluginDefault("UTF-8") final Charset charsetName,
            @PluginAttribute("exceptionPattern") final String exceptionPattern,
            @PluginElement("LoggerFields") final LoggerFields[] loggerFields,
            @PluginAttribute("advertise") @PluginDefault("false") final boolean advertise) {
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