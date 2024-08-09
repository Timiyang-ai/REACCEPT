public static Configuration loadConfiguration(String aConfig,
        PropertyResolver aOverridePropsResolver, boolean aOmitIgnoredModules)
        throws CheckstyleException
    {
        try {
            // figure out if this is a File or a URL
            URI uri;
            try {
                final URL url = new URL(aConfig);
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
                final File file = new File(aConfig);
                if (!file.exists()) {
                    throw new FileNotFoundException(aConfig);
                }
                uri = file.toURI();
            }
            final InputSource source = new InputSource(uri.toString());
            return loadConfiguration(source, aOverridePropsResolver,
                    aOmitIgnoredModules);
        }
        catch (final FileNotFoundException e) {
            throw new CheckstyleException("unable to find " + aConfig, e);
        }
        catch (final CheckstyleException e) {
                //wrap again to add file name info
            throw new CheckstyleException("unable to read " + aConfig + " - "
                    + e.getMessage(), e);
        }
    }