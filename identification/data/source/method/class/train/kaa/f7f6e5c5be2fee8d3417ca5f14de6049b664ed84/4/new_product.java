@Override
    public List<VersionDto> getLogSchemaVersionsByApplicationId(String applicationId) throws ControlServiceException {
        return logSchemaService.findLogSchemaVersionsByApplicationId(applicationId);
    }