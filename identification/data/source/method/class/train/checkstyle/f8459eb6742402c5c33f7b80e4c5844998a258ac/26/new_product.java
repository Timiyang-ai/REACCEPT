@Deprecated
    public static Configuration loadConfiguration(InputStream configStream,
        PropertyResolver overridePropsResolver, boolean omitIgnoredModules)
        throws CheckstyleException
    {
        return loadConfiguration(new InputSource(configStream),
                                 overridePropsResolver, omitIgnoredModules);
    }