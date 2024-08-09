@Test
  public void testGetEntity() {

    String entity = "Hello" + UUID.randomUUID().toString();  // gotta be unique
    String entityDescription = "Description of " + entity;
    String entityValue = "Value of " + entity;
    List<CreateValue> entityValues = new ArrayList<CreateValue>();
    entityValues.add(new CreateValue.Builder().value(entityValue).build());

    CreateEntityOptions.Builder optionsBuilder = new CreateEntityOptions.Builder();
    optionsBuilder.workspaceId(workspaceId);
    optionsBuilder.entity(entity);
    optionsBuilder.description(entityDescription);
    optionsBuilder.values(entityValues);
    service.createEntity(optionsBuilder.build()).execute();

    Date start = new Date();

    try {
      GetEntityOptions getOptions = new GetEntityOptions.Builder(workspaceId, entity).export(true).build();
      EntityExport response = service.getEntity(getOptions).execute();
      assertNotNull(response);
      assertNotNull(response.getEntityName());
      assertEquals(response.getEntityName(), entity);
      assertNotNull(response.getDescription());
      assertEquals(response.getDescription(), entityDescription);
      assertNotNull(response.getValues());
      assertNotNull(response.getCreated());
      assertNotNull(response.getUpdated());

      Date now = new Date();
      assertTrue(fuzzyBefore(response.getCreated(), now));
      assertTrue(fuzzyAfter(response.getCreated(), start));
      assertTrue(fuzzyBefore(response.getUpdated(), now));
      assertTrue(fuzzyAfter(response.getUpdated(), start));

      List<ValueExport> values = response.getValues();
      assertTrue(values.size() == 1);
      assertEquals(values.get(0).getValueText(), entityValue);
      assertTrue(fuzzyBefore(values.get(0).getCreated(), now));
      assertTrue(fuzzyAfter(values.get(0).getCreated(), start));
      assertTrue(fuzzyBefore(values.get(0).getUpdated(), now));
      assertTrue(fuzzyAfter(values.get(0).getUpdated(), start));

    } catch (Exception ex) {
      fail(ex.getMessage());
    } finally {
      // Clean up
      DeleteEntityOptions deleteOptions = new DeleteEntityOptions.Builder(workspaceId, entity).build();
      service.deleteEntity(deleteOptions).execute();
    }
  }