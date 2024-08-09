public static Configuration loadConfiguration(String config,
                                                  PropertyResolver overridePropsResolver,
                                                  IgnoredModulesOptions ignoredModulesOptions,
                                                  ThreadModeSettings threadModeSettings)
            throws CheckstyleException {
        // figure out if this is a File or a URL
        final URI uri = CommonUtil.getUriByFilename(config);
        final InputSource source = new InputSource(uri.toString());
        return loadConfiguration(source, overridePropsResolver,
                ignoredModulesOptions, threadModeSettings);
    }