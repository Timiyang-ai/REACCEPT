public static Configuration loadConfiguration(String aConfig,
            PropertyResolver aOverridePropsResolver) throws CheckstyleException
    {
        return loadConfiguration(aConfig, aOverridePropsResolver, false);
    }