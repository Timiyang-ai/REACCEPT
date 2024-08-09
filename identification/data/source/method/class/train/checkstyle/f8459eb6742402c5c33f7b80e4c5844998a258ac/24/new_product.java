@Deprecated
    public static Configuration loadConfiguration(InputStream aConfigStream,
        PropertyResolver aOverridePropsResolver, boolean aOmitIgnoredModules)
        throws CheckstyleException
    {
        return loadConfiguration(new InputSource(aConfigStream),
                                 aOverridePropsResolver, aOmitIgnoredModules);
    }