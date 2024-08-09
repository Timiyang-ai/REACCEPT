@Override
    public List<SchemaDto> getLogSchemaVersionsByApplicationId(String applicationId) throws ControlServiceException {
        return logSchemaService.findLogSchemaVersionsByApplicationId(applicationId);
    }