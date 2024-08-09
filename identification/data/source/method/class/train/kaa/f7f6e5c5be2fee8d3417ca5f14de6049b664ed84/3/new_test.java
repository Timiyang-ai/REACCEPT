@Test
    public void testGetUserNotificationSchemasByAppId() throws Exception {
        NotificationSchemaDto schemaDto = createNotificationSchema(null, NotificationTypeDto.USER);
        Assert.assertNotNull(schemaDto.getId());
        LOG.debug("Create notification schema with id {}", schemaDto.getId());
        List<VersionDto> foundSchema = client.getUserNotificationSchemas(schemaDto.getApplicationId());
        Assert.assertFalse(foundSchema.isEmpty());
        Assert.assertEquals(2, foundSchema.size());
        assertSchemasEquals(schemaDto, foundSchema.get(1));
    }