public static Configuration loadConfiguration(String aConfig,
        PropertyResolver aOverridePropsResolver, boolean aOmitIgnoredModules)
        throws CheckstyleException
    {
        InputStream bufferedStream = null;
        try {
            // figure out if this is a File or a URL
            InputStream configStream;
            try {
                final URL url = new URL(aConfig);
                configStream = url.openStream();
            }
            catch (final MalformedURLException ex) {
                configStream = new FileInputStream(aConfig);
            }
            bufferedStream = new BufferedInputStream(configStream);

            return loadConfiguration(bufferedStream, aOverridePropsResolver,
                    aOmitIgnoredModules);
        }
        catch (final FileNotFoundException e) {
            throw new CheckstyleException("unable to find " + aConfig, e);
        }
        catch (final IOException e) {
            throw new CheckstyleException("unable to read " + aConfig, e);
        }
        catch (final CheckstyleException e) {
                //wrap again to add file name info
            throw new CheckstyleException("unable to read " + aConfig + " - "
                    + e.getMessage(), e);
        }
        finally {
            if (bufferedStream != null) {
                try {
                    bufferedStream.close();
                }
                catch (final IOException e) {
                    // cannot throw another exception.
                    ;
                }
            }
        }
    }