public static Pair<Boolean, Properties> getAndValidateConfigFileContentsOnPath(final Path configFilePath,
                                                                                   final boolean errorOnMissingConfigKey) {

        Utils.nonNull(configFilePath);

        boolean isValid = true;

        // Read in the contents of the config file:
        final Properties configProperties = new Properties();
        try ( final InputStream inputStream = Files.newInputStream(configFilePath, StandardOpenOption.READ) ) {
            configProperties.load(inputStream);
        }
        catch (final Exception ex) {
            throw new UserException.BadInput("Unable to read from XSV config file: " + configFilePath.toUri().toString(), ex);
        }

        // Validate that it has the correct keys:
        isValid = Stream.of(
                    DataSourceUtils.CONFIG_FILE_FIELD_NAME_SRC_FILE,
                    DataSourceUtils.CONFIG_FILE_FIELD_NAME_VERSION,
                    DataSourceUtils.CONFIG_FILE_FIELD_NAME_ORIGIN_LOCATION,
                    DataSourceUtils.CONFIG_FILE_FIELD_NAME_PREPROCESSING_SCRIPT,
                    DataSourceUtils.CONFIG_FILE_FIELD_NAME_CONTIG_COLUMN,
                    DataSourceUtils.CONFIG_FILE_FIELD_NAME_START_COLUMN,
                    DataSourceUtils.CONFIG_FILE_FIELD_NAME_END_COLUMN,
                    DataSourceUtils.CONFIG_FILE_FIELD_NAME_XSV_DELIMITER,
                    DataSourceUtils.CONFIG_FILE_FIELD_NAME_NAME)
                .map( key -> configPropertiesContainsKey(configProperties, key, configFilePath, errorOnMissingConfigKey))
                .allMatch( result -> result );

        return Pair.of(isValid, configProperties);
    }