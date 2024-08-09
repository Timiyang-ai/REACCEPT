public static Configuration loadConfiguration(String config,
            PropertyResolver overridePropsResolver, ThreadModeSettings threadModeSettings)
            throws CheckstyleException {
        return loadConfiguration(config, overridePropsResolver, false, threadModeSettings);
    }