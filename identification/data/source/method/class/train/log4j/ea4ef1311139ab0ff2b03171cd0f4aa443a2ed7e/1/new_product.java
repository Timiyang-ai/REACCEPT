@PluginFactory
    public static PatternLayout createLayout(@PluginAttr("pattern") String pattern,
                                             @PluginConfiguration Configuration config,
                                             @PluginElement("replace") RegexReplacement replace,
                                             @PluginAttr("charset") String charset) {
        Charset c = Charset.isSupported("UTF-8") ? Charset.forName("UTF-8") : Charset.defaultCharset();
        if (charset != null) {
            if (Charset.isSupported(charset)) {
                c = Charset.forName(charset);
            } else {
                LOGGER.error("Charset " + charset + " is not supported for layout, using " + c.displayName());
            }
        }
        return new PatternLayout(config, replace, pattern == null ? DEFAULT_CONVERSION_PATTERN : pattern, c);
    }