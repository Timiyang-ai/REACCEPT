public static Configuration loadConfiguration(String config,
            PropertyResolver overridePropsResolver) throws CheckstyleException
    {
        return loadConfiguration(config, overridePropsResolver, false);
    }