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
            final String message = String.format("%s - %s:%s:%s", UNABLE_TO_PARSE_EXCEPTION_PREFIX,
                    e.getMessage(), e.getLineNumber(), e.getColumnNumber());
            throw new CheckstyleException(message, e);
        }
        catch (final ParserConfigurationException | IOException | SAXException e) {
            throw new CheckstyleException(UNABLE_TO_PARSE_EXCEPTION_PREFIX, e);
        }
    }