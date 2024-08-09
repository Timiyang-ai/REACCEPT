public static Configuration loadConfiguration(String config,
        PropertyResolver overridePropsResolver, boolean omitIgnoredModules)
        throws CheckstyleException {
        // figure out if this is a File or a URL
        URI uri;
        try {
            final URL url = new URL(config);
            uri = url.toURI();
        }
        catch (final URISyntaxException | MalformedURLException ignored) {
            uri = null;
        }

        if (uri == null) {
            final File file = new File(config);
            if (file.exists()) {
                uri = file.toURI();
            }
            else {
                // check to see if the file is in the classpath
                try {
                    final URL configUrl = ConfigurationLoader.class
                            .getResource(config);
                    if (configUrl == null) {
                        throw new CheckstyleException(UNABLE_TO_FIND_EXCEPTION_PREFIX + config);
                    }
                    uri = configUrl.toURI();
                }
                catch (final URISyntaxException e) {
                    throw new CheckstyleException(UNABLE_TO_FIND_EXCEPTION_PREFIX + config, e);
                }
            }
        }
        final InputSource source = new InputSource(uri.toString());
        return loadConfiguration(source, overridePropsResolver,
                omitIgnoredModules);
    }