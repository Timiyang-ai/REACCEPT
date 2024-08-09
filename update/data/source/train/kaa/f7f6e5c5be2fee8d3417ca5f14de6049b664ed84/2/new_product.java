@Override
    public List<VersionDto> getProfileSchemaVersionsByApplicationId(String applicationId) throws ControlServiceException {
        return profileService.findProfileSchemaVersionsByAppId(applicationId);
    }