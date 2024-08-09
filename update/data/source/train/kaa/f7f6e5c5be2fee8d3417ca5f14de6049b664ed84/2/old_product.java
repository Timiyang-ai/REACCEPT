@Override
    public List<SchemaDto> getProfileSchemaVersionsByApplicationId(String applicationId) throws ControlServiceException {
        return profileService.findProfileSchemaVersionsByAppId(applicationId);
    }