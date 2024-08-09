@Override
    public List<SchemaDto> getConfigurationSchemaVersionsByApplicationId(String applicationId) throws ControlServiceException {
        return configurationService.findConfigurationSchemaVersionsByAppId(applicationId);
    }