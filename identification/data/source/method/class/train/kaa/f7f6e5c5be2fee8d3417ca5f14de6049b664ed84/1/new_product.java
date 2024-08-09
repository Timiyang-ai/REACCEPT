@Override
    public List<VersionDto> getUserNotificationSchemasByAppId(String applicationId) throws ControlServiceException {
        return notificationService.findUserNotificationSchemasByAppId(applicationId);
    }