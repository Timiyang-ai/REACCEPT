public static Configuration loadConfiguration(
        String aConfig, PropertyResolver aOverridePropsResolver)
        throws CheckstyleException
    {
        try {
            final ConfigurationLoader loader =
                new ConfigurationLoader(aOverridePropsResolver);
            // figure out if this is a File or a URL
            InputStream configStream;
            try {
                URL url = new URL(aConfig);
                configStream = url.openStream();
            }
            catch (MalformedURLException ex) {
                configStream = new FileInputStream(aConfig);
            }
            final InputStream bufferedStream =
                new BufferedInputStream(configStream);
            loader.parseInputStream(bufferedStream);
            bufferedStream.close();
            return loader.getConfiguration();
        }
        catch (FileNotFoundException e) {
            throw new CheckstyleException(
                "unable to find " + aConfig, e);
        }
        catch (ParserConfigurationException e) {
            throw new CheckstyleException(
                "unable to parse " + aConfig, e);
        }
        catch (SAXParseException e) {
            throw new CheckstyleException("unable to parse "
                    + aConfig + " - " + e.getMessage() + ":" + e.getLineNumber()
                    + ":" + e.getColumnNumber(), e);
        }
        catch (SAXException e) {
            throw new CheckstyleException("unable to parse "
                    + aConfig + " - " + e.getMessage(), e);
        }
        catch (IOException e) {
            throw new CheckstyleException("unable to read " + aConfig, e);
        }
    }