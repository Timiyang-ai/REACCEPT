@Test
  public void testCreateValue() {

    String entity = "beverage";
    String entityValue = "coffee" + UUID.randomUUID().toString();

    // metadata
    Map<String, Object> valueMetadata = new HashMap<String, Object>();
    String metadataValue = "value for " + entityValue;
    valueMetadata.put("key", metadataValue);

    try {
      CreateEntityOptions createOptions = new CreateEntityOptions.Builder(workspaceId, entity).build();
      service.createEntity(createOptions).execute();
    } catch (Exception ex) {
      // Exception is okay if is for Unique Violation
      assertTrue(ex.getLocalizedMessage().startsWith("Unique Violation"));
    }

    Date start = new Date();

    CreateValueOptions createOptions = new CreateValueOptions.Builder()
            .workspaceId(workspaceId)
            .entity(entity)
            .value(entityValue)
            .metadata(valueMetadata)
            .build();
    Value response = service.createValue(createOptions).execute();

    try {
      assertNotNull(response);
      assertNotNull(response.getValueText());
      assertEquals(response.getValueText(), entityValue);
      assertNotNull(response.getCreated());
      assertNotNull(response.getUpdated());
      assertNotNull(response.getMetadata());

      Date now = new Date();
      assertTrue(fuzzyBefore(response.getCreated(), now));
      assertTrue(fuzzyAfter(response.getCreated(), start));
      assertTrue(fuzzyBefore(response.getUpdated(), now));
      assertTrue(fuzzyAfter(response.getUpdated(), start));

      // metadata
      assertNotNull(response.getMetadata());
      assertNotNull(response.getMetadata().get("key"));
      assertEquals(response.getMetadata().get("key"), metadataValue);
    } catch (Exception ex) {
      fail(ex.getMessage());
    } finally {
      // Clean up
      DeleteValueOptions deleteOptions =
              new DeleteValueOptions.Builder(workspaceId, entity, entityValue).build();
      service.deleteValue(deleteOptions).execute();
    }
  }