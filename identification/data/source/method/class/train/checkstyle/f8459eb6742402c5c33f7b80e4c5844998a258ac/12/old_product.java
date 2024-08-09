public static Configuration loadConfiguration(InputSource configSource,
        PropertyResolver overridePropsResolver, boolean omitIgnoredModules)
        throws CheckstyleException {
        try {
            final ConfigurationLoader loader =
                new ConfigurationLoader(overridePropsResolver,
                                        omitIgnoredModules);
            loader.parseInputSource(configSource);
            return loader.getConfiguration();
        }
        catch (final ParserConfigurationException e) {
            throw new CheckstyleException(
                "unable to parse configuration stream", e);
        }
        catch (final SAXParseException e) {
            throw new CheckstyleException("unable to parse configuration stream"
                    + " - " + e.getMessage() + ":" + e.getLineNumber()
                    + ":" + e.getColumnNumber(), e);
        }
        catch (final SAXException e) {
            throw new CheckstyleException("unable to parse configuration stream"
                    + " - " + e.getMessage(), e);
        }
        catch (final IOException e) {
            throw new CheckstyleException("unable to read from stream", e);
        }
    }