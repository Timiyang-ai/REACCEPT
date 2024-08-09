public static Configuration loadConfiguration(String config,
        PropertyResolver overridePropsResolver, boolean omitIgnoredModules)
        throws CheckstyleException
    {
        try {
            // figure out if this is a File or a URL
            URI uri;
            try {
                final URL url = new URL(config);
                uri = url.toURI();
            }
            catch (final MalformedURLException ex) {
                uri = null;
            }
            catch (final URISyntaxException ex) {
                // URL violating RFC 2396
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
                            throw new FileNotFoundException(config);
                        }
                        uri = configUrl.toURI();
                    }
                    catch (final URISyntaxException e) {
                        throw new FileNotFoundException(config);
                    }
                }
            }
            final InputSource source = new InputSource(uri.toString());
            return loadConfiguration(source, overridePropsResolver,
                    omitIgnoredModules);
        }
        catch (final FileNotFoundException e) {
            throw new CheckstyleException("unable to find " + config, e);
        }
        catch (final CheckstyleException e) {
                //wrap again to add file name info
            throw new CheckstyleException("unable to read " + config + " - "
                    + e.getMessage(), e);
        }
    }