@Test
  @Ignore("To be run locally until we fix the Rate limitation issue")
  public void testListEntities() {

    String entity = "Hello" + UUID.randomUUID().toString(); // gotta be unique

    try {
      ListEntitiesOptions listOptions = new ListEntitiesOptions.Builder(workspaceId).build();
      EntityCollection response = service.listEntities(listOptions).execute();
      assertNotNull(response);
      assertNotNull(response.getEntities());
      assertNotNull(response.getPagination());
      assertNotNull(response.getPagination().getRefreshUrl());
      // nextUrl may be null

      // Now add an entity and make sure we get it back
      String entityDescription = "Description of " + entity;
      String entityValue = "Value of " + entity;
      CreateEntityOptions options = new CreateEntityOptions.Builder(workspaceId, entity)
          .description(entityDescription)
          .addValue(new CreateValue.Builder(entityValue).build())
          .build();
      service.createEntity(options).execute();

      ListEntitiesOptions listOptions2 = listOptions.newBuilder()
          .sort("-updated").pageLimit(5L).export(true).build();
      EntityCollection response2 = service.listEntities(listOptions2).execute();
      assertNotNull(response2);
      assertNotNull(response2.getEntities());

      List<EntityExport> entities = response2.getEntities();
      EntityExport ieResponse = null;
      for (EntityExport resp : entities) {
        if (resp.getEntityName().equals(entity)) {
          ieResponse = resp;
          break;
        }
      }

      assertNotNull(ieResponse);
      assertNotNull(ieResponse.getDescription());
      assertEquals(ieResponse.getDescription(), entityDescription);
      assertNotNull(ieResponse.getValues());
      assertTrue(ieResponse.getValues().size() == 1);
      assertTrue(ieResponse.getValues().get(0).getValueText().equals(entityValue));
    } catch (Exception ex) {
      fail(ex.getMessage());
    } finally {
      // Clean up
      DeleteEntityOptions deleteOptions = new DeleteEntityOptions.Builder(workspaceId, entity).build();
      service.deleteEntity(deleteOptions).execute();
    }
  }