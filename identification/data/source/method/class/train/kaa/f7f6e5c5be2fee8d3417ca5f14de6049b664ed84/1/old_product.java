@Override
    public List<SchemaDto> getUserNotificationSchemasByAppId(String applicationId) throws ControlServiceException {
        return notificationService.findUserNotificationSchemasByAppId(applicationId);
    }