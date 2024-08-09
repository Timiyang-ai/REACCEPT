@PluginFactory
    public static TlsSyslogAppender createAppender(@PluginAttribute("host") final String host,
                                                   @PluginAttribute("port") final String portNum,
                                                   @PluginElement("Ssl") final SslConfiguration sslConfig,
                                                   @PluginAttribute("reconnectionDelay") final String delay,
                                                   @PluginAttribute("immediateFail") final String immediateFail,
                                                   @PluginAttribute("name") final String name,
                                                   @PluginAttribute("immediateFlush") final String immediateFlush,
                                                   @PluginAttribute("ignoreExceptions") final String ignore,
                                                   @PluginAttribute("facility") final String facility,
                                                   @PluginAttribute("id") final String id,
                                                   @PluginAttribute("enterpriseNumber") final String ein,
                                                   @PluginAttribute("includeMDC") final String includeMDC,
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
                                                   @PluginElement("LoggerFields") final LoggerFields[] loggerFields,
                                                   @PluginAttribute("advertise") final String advertise) {
        final boolean isFlush = Booleans.parseBoolean(immediateFlush, true);
        final boolean ignoreExceptions = Booleans.parseBoolean(ignore, true);
        final int reconnectDelay = AbstractAppender.parseInt(delay, 0);
        final boolean fail = Booleans.parseBoolean(immediateFail, true);
        final int port = AbstractAppender.parseInt(portNum, 0);
        final boolean isAdvertise = Boolean.parseBoolean(advertise);
        @SuppressWarnings("unchecked")
        final Layout<? extends Serializable> layout = RFC5424.equalsIgnoreCase(format) ?
                Rfc5424Layout.createLayout(facility, id, ein, includeMDC, mdcId, mdcPrefix, eventPrefix, includeNL,
                    escapeNL, appName, msgId, excludes, includes, required, exceptionPattern, "true" ,loggerFields,
                    config) :
                SyslogLayout.createLayout(facility, includeNL, escapeNL, charsetName);

        if (name == null) {
            LOGGER.error("No name provided for TlsSyslogAppender");
            return null;
        }
        final AbstractSocketManager manager = createSocketManager(sslConfig, host, port, reconnectDelay, fail, layout);
        if (manager == null) {
            return null;
        }

        return new TlsSyslogAppender(name, layout, filter, ignoreExceptions, isFlush, manager,
                isAdvertise ? config.getAdvertiser() : null);
    }