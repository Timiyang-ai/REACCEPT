public static Properties getAndValidateConfigFileContents(final Path configFilePath) {

        Utils.nonNull(configFilePath);

        // Read in the contents of the config file:
        final Properties configFileContents = new Properties();
        try ( final InputStream inputStream = Files.newInputStream(configFilePath, StandardOpenOption.READ) ) {
            configFileContents.load(inputStream);
        }
        catch (final Exception ex) {
            throw new UserException.BadInput("Unable to read from XSV config file: " + configFilePath.toUri().toString(), ex);
        }

        // Validate that it has the right keys:
        assertConfigPropertiesContainsKey(configFileContents, CONFIG_FILE_CONTIG_COLUMN_KEY, configFilePath);
        assertConfigPropertiesContainsKey(configFileContents, CONFIG_FILE_START_COLUMN_KEY, configFilePath);
        assertConfigPropertiesContainsKey(configFileContents, CONFIG_FILE_END_COLUMN_KEY, configFilePath);
        assertConfigPropertiesContainsKey(configFileContents, CONFIG_FILE_DELIMITER_KEY, configFilePath);
        assertConfigPropertiesContainsKey(configFileContents, CONFIG_FILE_DATA_SOURCE_NAME_KEY, configFilePath);

        return configFileContents;
    }