@PluginFactory
    public static XMLLayout createLayout(@PluginAttr("locationInfo") String locationInfo,
                                         @PluginAttr("properties") String properties,
                                         @PluginAttr("complete") String complete,
                                         @PluginAttr("charset") String charset) {
        Charset c = Charset.isSupported("UTF-8") ? Charset.forName("UTF-8") : Charset.defaultCharset();
        if (charset != null) {
            if (Charset.isSupported(charset)) {
                c = Charset.forName(charset);
            } else {
                LOGGER.error("Charset " + charset + " is not supported for layout, using " + c.displayName());
            }
        }
        boolean info = locationInfo == null ? false : Boolean.valueOf(locationInfo);
        boolean props = properties == null ? false : Boolean.valueOf(properties);
        boolean comp = complete == null ? false : Boolean.valueOf(complete);
        return new XMLLayout(info, props, comp, c);
    }