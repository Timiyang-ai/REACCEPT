public static Configuration loadConfiguration(String config,
        PropertyResolver overridePropsResolver, boolean omitIgnoredModules)
            throws CheckstyleException {
        return loadConfiguration(config, overridePropsResolver, omitIgnoredModules,
                ThreadModeSettings.SINGLE_THREAD_MODE_INSTANCE);
    }