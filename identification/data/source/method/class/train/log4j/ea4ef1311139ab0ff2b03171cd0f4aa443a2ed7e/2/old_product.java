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
                logger.error("Charset " + charset + " is not supported for layout, using " + c.displayName());
            }
        }
        if (pattern != null) {
            return new PatternLayout(config, replace, pattern, c);
        }
        logger.error("No pattern specified for PatternLayout");
        return null;
    }