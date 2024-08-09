public static Configuration loadConfiguration(String aConfigFname,
                                                  Properties aOverrideProps)
        throws CheckstyleException
    {
        try {
            final ConfigurationLoader loader =
                new ConfigurationLoader(aOverrideProps);
            loader.parseFile(aConfigFname);
            return loader.getConfiguration();
        }
        catch (FileNotFoundException e) {
            throw new CheckstyleException(
                "unable to find " + aConfigFname, e);
        }
        catch (ParserConfigurationException e) {
            throw new CheckstyleException(
                "unable to parse " + aConfigFname, e);
        }
        catch (SAXException e) {
            throw new CheckstyleException("unable to parse "
                    + aConfigFname + " - " + e.getMessage(), e);
        }
        catch (IOException e) {
            throw new CheckstyleException("unable to read " + aConfigFname, e);
        }
    }