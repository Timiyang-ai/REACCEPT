public static Configuration loadConfiguration(InputStream aConfigStream,
        PropertyResolver aOverridePropsResolver, boolean aOmitIgnoredModules)
        throws CheckstyleException
    {
        try {
            final ConfigurationLoader loader =
                new ConfigurationLoader(aOverridePropsResolver,
                                        aOmitIgnoredModules);
            loader.parseInputStream(aConfigStream);
            return loader.getConfiguration();
        }
        catch (ParserConfigurationException e) {
            throw new CheckstyleException(
                "unable to parse configuration stream", e);
        }
        catch (SAXParseException e) {
            throw new CheckstyleException("unable to parse configuration stream"
                    + " - " + e.getMessage() + ":" + e.getLineNumber()
                    + ":" + e.getColumnNumber(), e);
        }
        catch (SAXException e) {
            throw new CheckstyleException("unable to parse configuration stream"
                    + " - " + e.getMessage(), e);
        }
        catch (IOException e) {
            throw new CheckstyleException("unable to read from stream", e);
        }
    }