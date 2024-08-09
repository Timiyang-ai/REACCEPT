@Test
  public void testUpdateValue() {

    String entity = "beverage";
    String entityValue1 = "coffee" + UUID.randomUUID().toString();
    String entityValue2 = "coffee" + UUID.randomUUID().toString();
    String synonym1 = "java";
    String synonym2 = "joe";

    // metadata
    Map<String, Object> valueMetadata = new HashMap<String, Object>();
    String metadataValue = "value for " + entityValue2;
    valueMetadata.put("key", metadataValue);

    try {
      CreateEntityOptions createOptions = new CreateEntityOptions.Builder(workspaceId, entity).build();
      service.createEntity(createOptions).execute();
    } catch (Exception ex) {
      // Exception is okay if is for Unique Violation
      assertTrue(ex.getLocalizedMessage().startsWith("Unique Violation"));
    }

    try {
      CreateValueOptions createOptions = new CreateValueOptions.Builder(workspaceId, entity, entityValue1).build();
      service.createValue(createOptions).execute();
    } catch (Exception ex) {
      // Exception is okay if is for Unique Violation
      assertTrue(ex.getLocalizedMessage().startsWith("Unique Violation"));
    }

    Date start = new Date();

    UpdateValueOptions updateOptions = new UpdateValueOptions.Builder()
        .workspaceId(workspaceId)
        .entity(entity)
        .value(entityValue1)
        .newValue(entityValue2)
        .newSynonyms(new ArrayList<String>(Arrays.asList(synonym1, synonym2)))
        .newMetadata(valueMetadata)
        .build();
    Value response = service.updateValue(updateOptions).execute();

    try {
      assertNotNull(response);
      assertNotNull(response.getValue());
      assertEquals(response.getValue(), entityValue2);
      assertNotNull(response.getCreated());
      assertNotNull(response.getUpdated());

      Date now = new Date();
      assertTrue(fuzzyBefore(response.getCreated(), now));
      //assertTrue(fuzzyAfter(response.getCreated(), start));
      assertTrue(fuzzyBefore(response.getUpdated(), now));
      assertTrue(fuzzyAfter(response.getUpdated(), start));

      GetValueOptions getOptions =
          new GetValueOptions.Builder(workspaceId, entity, entityValue2)
              .export(true)
              .build();
      ValueExport vResponse = service.getValue(getOptions).execute();

      assertNotNull(vResponse);
      assertNotNull(vResponse.getValue());
      assertEquals(vResponse.getValue(), entityValue2);
      assertNotNull(vResponse.getCreated());
      assertNotNull(vResponse.getUpdated());

      assertEquals(vResponse.getCreated(), response.getCreated());
      assertEquals(vResponse.getUpdated(), response.getUpdated());

      assertNotNull(vResponse.getSynonyms());
      assertTrue(vResponse.getSynonyms().size() == 2);
      assertTrue(vResponse.getSynonyms().contains(synonym1));
      assertTrue(vResponse.getSynonyms().contains(synonym2));

      // metadata
      assertNotNull(response.getMetadata());
      assertNotNull(response.getMetadata().get("key"));
      assertEquals(response.getMetadata().get("key"), metadataValue);

    } catch (Exception ex) {
      fail(ex.getMessage());
    } finally {
      // Clean up
      DeleteValueOptions deleteOptions =
          new DeleteValueOptions.Builder(workspaceId, entity, entityValue2).build();
      service.deleteValue(deleteOptions).execute();
    }
  }