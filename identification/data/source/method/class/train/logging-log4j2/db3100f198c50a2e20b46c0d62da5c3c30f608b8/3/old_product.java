@PluginFactory
    public static SyslogAppender createAppender(@PluginAttr("host") String host,
                                                @PluginAttr("port") String portNum,
                                                @PluginAttr("protocol") String protocol,
                                                @PluginAttr("reconnectionDelay") String delay,
                                                @PluginAttr("name") String name,
                                                @PluginAttr("immediateFlush") String immediateFlush,
                                                @PluginAttr("suppressExceptions") String suppress,
                                                @PluginAttr("facility") String facility,
                                                @PluginAttr("id") String id,
                                                @PluginAttr("enterpriseNumber") String ein,
                                                @PluginAttr("includeMDC") String includeMDC,
                                                @PluginAttr("mdcId") String mdcId,
                                                @PluginAttr("newLine") String includeNL,
                                                @PluginAttr("appName") String appName,
                                                @PluginAttr("messageId") String msgId,
                                                @PluginAttr("mdcExcludes") String excludes,
                                                @PluginAttr("mdcIncludes") String includes,
                                                @PluginAttr("mdcRequired") String required,
                                                @PluginAttr("format") String format,
                                                @PluginElement("filters") Filter filter,
                                                @PluginConfiguration Configuration config,
                                                @PluginAttr("charset") String charset) {

        boolean isFlush = immediateFlush == null ? true : Boolean.valueOf(immediateFlush);;
        boolean handleExceptions = suppress == null ? true : Boolean.valueOf(suppress);
        int reconnectDelay = delay == null ? 0 : Integer.parseInt(delay);
        int port = portNum == null ? 0 : Integer.parseInt(portNum);
        Charset c = Charset.isSupported("UTF-8") ? Charset.forName("UTF-8") : Charset.defaultCharset();
        if (charset != null) {
            if (Charset.isSupported(charset)) {
                c = Charset.forName(charset);
            } else {
                logger.error("Charset " + charset + " is not supported for layout, using " + c.displayName());
            }
        }
        Layout layout = (format.equalsIgnoreCase(RFC5424)) ?
            RFC5424Layout.createLayout(facility, id, ein, includeMDC, mdcId, includeNL, appName,  msgId,
                excludes, includes, required, charset, config) :
            SyslogLayout.createLayout(facility, includeNL, charset);

        if (name == null) {
            logger.error("No name provided for SyslogAppender");
            return null;
        }
        AbstractSocketManager manager = createSocketManager(protocol, host, port, reconnectDelay);
        if (manager == null) {
            return null;
        }

        return new SyslogAppender(name, layout, filter, handleExceptions, isFlush, manager);
    }