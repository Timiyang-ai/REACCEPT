public static Configuration loadConfiguration(String config,
        PropertyResolver overridePropsResolver, boolean omitIgnoredModules)
            throws CheckstyleException {
        // figure out if this is a File or a URL
        final URI uri = CommonUtils.getUriByFilename(config);
        final InputSource source = new InputSource(uri.toString());
        return loadConfiguration(source, overridePropsResolver,
                omitIgnoredModules);
    }