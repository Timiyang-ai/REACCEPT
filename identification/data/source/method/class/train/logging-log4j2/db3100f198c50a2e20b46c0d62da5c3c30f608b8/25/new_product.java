@PluginFactory
    public static SyslogAppender createAppender(@PluginAttr("host") final String host,
                                                @PluginAttr("port") final String portNum,
                                                @PluginAttr("protocol") final String protocol,
                                                @PluginAttr("reconnectionDelay") final String delay,
                                                @PluginAttr("name") final String name,
                                                @PluginAttr("immediateFlush") final String immediateFlush,
                                                @PluginAttr("suppressExceptions") final String suppress,
                                                @PluginAttr("facility") final String facility,
                                                @PluginAttr("id") final String id,
                                                @PluginAttr("enterpriseNumber") final String ein,
                                                @PluginAttr("includeMDC") final String includeMDC,
                                                @PluginAttr("mdcId") final String mdcId,
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
                                                @PluginAttr("charset") final String charset,
                                                @PluginAttr("exceptionPattern") final String exceptionPattern) {

        final boolean isFlush = immediateFlush == null ? true : Boolean.valueOf(immediateFlush);
        final boolean handleExceptions = suppress == null ? true : Boolean.valueOf(suppress);
        final int reconnectDelay = delay == null ? 0 : Integer.parseInt(delay);
        final int port = portNum == null ? 0 : Integer.parseInt(portNum);
        Charset c = Charset.isSupported("UTF-8") ? Charset.forName("UTF-8") : Charset.defaultCharset();
        if (charset != null) {
            if (Charset.isSupported(charset)) {
                c = Charset.forName(charset);
            } else {
                LOGGER.error("Charset " + charset + " is not supported for layout, using " + c.displayName());
            }
        }
        final Layout layout = (RFC5424.equalsIgnoreCase(format)) ?
            RFC5424Layout.createLayout(facility, id, ein, includeMDC, mdcId, includeNL, escapeNL, appName,
                msgId, excludes, includes, required, charset, exceptionPattern, config) :
            SyslogLayout.createLayout(facility, includeNL, escapeNL, charset);

        if (name == null) {
            LOGGER.error("No name provided for SyslogAppender");
            return null;
        }
        final String prot = protocol != null ? protocol : Protocol.UDP.name();
        final AbstractSocketManager manager = createSocketManager(prot, host, port, reconnectDelay);
        if (manager == null) {
            return null;
        }

        return new SyslogAppender(name, layout, filter, handleExceptions, isFlush, manager);
    }