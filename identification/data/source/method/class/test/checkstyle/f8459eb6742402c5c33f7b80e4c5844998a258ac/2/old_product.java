public static Configuration loadConfiguration(String aConfigFname,
                                                  Properties aOverrideProps)
        throws CheckstyleException
    {
        try {
            final ConfigurationLoader loader = new ConfigurationLoader();
            loader.mOverrideProps = aOverrideProps;
            loader.parseFile(aConfigFname);
            return loader.getConfiguration();
        }
        catch (FileNotFoundException e) {
            throw new CheckstyleException("unable to find " + aConfigFname);
        }
        catch (ParserConfigurationException e) {
            throw new CheckstyleException("unable to parse " + aConfigFname);
        }
        catch (SAXException e) {
            throw new CheckstyleException("unable to parse "
                    + aConfigFname + " - " + e.getMessage());
        }
        catch (IOException e) {
            throw new CheckstyleException("unable to read " + aConfigFname);
        }
    }