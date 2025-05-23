@PluginFactory
    public static <S extends Serializable> SyslogAppender<S> createAppender(@PluginAttr("host") final String host,
                                                @PluginAttr("port") final String portNum,
                                                @PluginAttr("protocol") final String protocol,
                                                @PluginAttr("reconnectionDelay") final String delay,
                                                @PluginAttr("immediateFail") final String immediateFail,
                                                @PluginAttr("name") final String name,
                                                @PluginAttr("immediateFlush") final String immediateFlush,
                                                @PluginAttr("suppressExceptions") final String suppress,
                                                @PluginAttr("facility") final String facility,
                                                @PluginAttr("id") final String id,
                                                @PluginAttr("enterpriseNumber") final String ein,
                                                @PluginAttr("includeMDC") final String includeMDC,
                                                @PluginAttr("mdcId") final String mdcId,
                                                @PluginAttr("mdcPrefix") final String mdcPrefix,
                                                @PluginAttr("eventPrefix") final String eventPrefix,
                                                @PluginAttr("newLine") final String includeNL,
                                                @PluginAttr("newLineEscape") final String escapeNL,
                                                @PluginAttr("appName") final String appName,
                                                @PluginAttr("messageId") final String msgId,
                                                @PluginAttr("mdcExcludes") final String excludes,
                                                @PluginAttr("mdcIncludes") final String includes,
                                                @PluginAttr("mdcRequired") final String required,
                                                @PluginAttr("format") final String format,
                                                @PluginElement("filters") final Filter filter,
                                                @PluginConfiguration final Configuration config,
                                                @PluginAttr("charset") final String charsetName,
                                                @PluginAttr("exceptionPattern") final String exceptionPattern,
                                                @PluginAttr("advertise") final String advertise) {

        final boolean isFlush = immediateFlush == null ? true : Boolean.valueOf(immediateFlush);
        final boolean handleExceptions = suppress == null ? true : Boolean.valueOf(suppress);
        final int reconnectDelay = delay == null ? 0 : Integer.parseInt(delay);
        final boolean fail = immediateFail == null ? true : Boolean.valueOf(immediateFail);
        final int port = portNum == null ? 0 : Integer.parseInt(portNum);
        boolean isAdvertise = advertise == null ? false : Boolean.valueOf(advertise);
        @SuppressWarnings("unchecked")
        final Layout<S> layout = (Layout<S>)(RFC5424.equalsIgnoreCase(format) ?
            RFC5424Layout.createLayout(facility, id, ein, includeMDC, mdcId, mdcPrefix, eventPrefix, includeNL,
                escapeNL, appName, msgId, excludes, includes, required, charsetName, exceptionPattern, config) :
            SyslogLayout.createLayout(facility, includeNL, escapeNL, charsetName));

        if (name == null) {
            LOGGER.error("No name provided for SyslogAppender");
            return null;
        }
        final String prot = protocol != null ? protocol : Protocol.UDP.name();
        final AbstractSocketManager manager = createSocketManager(prot, host, port, reconnectDelay, fail);
        if (manager == null) {
            return null;
        }

        return new SyslogAppender<S>(name, layout, filter, handleExceptions, isFlush, manager,
                isAdvertise ? config.getAdvertiser() : null);
    }