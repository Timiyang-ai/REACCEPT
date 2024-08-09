@PluginFactory
    public static HTMLLayout createLayout(@PluginAttr("locationInfo") String locationInfo,
                                          @PluginAttr("title") String title,
                                          @PluginAttr("contentType") String contentType,
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
        if (title == null) {
            title = DEFAULT_TITLE;
        }
        if (contentType == null) {
            contentType = DEFAULT_CONTENT_TYPE;
        }
        return new HTMLLayout(info, title, contentType, c);
    }