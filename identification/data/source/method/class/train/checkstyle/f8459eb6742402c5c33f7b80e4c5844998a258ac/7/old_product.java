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
        catch (final SAXParseException ex) {
            final String message = String.format(Locale.ROOT, "%s - %s:%s:%s",
                    UNABLE_TO_PARSE_EXCEPTION_PREFIX,
                    ex.getMessage(), ex.getLineNumber(), ex.getColumnNumber());
            throw new CheckstyleException(message, ex);
        }
        catch (final ParserConfigurationException | IOException | SAXException ex) {
            throw new CheckstyleException(UNABLE_TO_PARSE_EXCEPTION_PREFIX, ex);
        }
    }