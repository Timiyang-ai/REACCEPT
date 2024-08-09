@Deprecated
    public static Configuration loadConfiguration(InputSource configSource,
            PropertyResolver overridePropsResolver, boolean omitIgnoredModules)
            throws CheckstyleException {
        return loadConfiguration(configSource, overridePropsResolver,
                omitIgnoredModules, ThreadModeSettings.SINGLE_THREAD_MODE_INSTANCE);
    }