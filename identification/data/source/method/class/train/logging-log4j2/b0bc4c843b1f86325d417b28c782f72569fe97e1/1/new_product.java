@PluginBuilderFactory
    public static <B extends Builder<B>> B newSyslogAppenderBuilder() {
        return new Builder<B>().asBuilder();
    }