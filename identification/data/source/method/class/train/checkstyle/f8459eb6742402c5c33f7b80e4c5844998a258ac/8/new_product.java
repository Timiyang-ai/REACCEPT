public static Configuration loadConfiguration(InputSource configSource,
        PropertyResolver overridePropsResolver, boolean omitIgnoredModules)
        throws CheckstyleException {
        try {
            final ConfigurationLoader loader =
                new ConfigurationLoader(overridePropsResolver,
                                        omitIgnoredModules);
            loader.parseInputSource(configSource);
            return loader.configuration;
        }
        catch (final SAXParseException e) {
            throw new CheckstyleException("unable to parse configuration stream"
                    + " - " + e.getMessage() + ":" + e.getLineNumber()
                    + ":" + e.getColumnNumber(), e);
        }
        catch (final ParserConfigurationException | IOException | SAXException e) {
            throw new CheckstyleException("unable to parse configuration stream", e);
        }
    }