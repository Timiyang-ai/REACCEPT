@Test
  public void testGetSynonym() {

    String entity = "beverage";
    String entityValue = "orange juice";
    String synonym = "OJ";

    try {
      CreateEntityOptions createOptions = new CreateEntityOptions.Builder(workspaceId, entity).build();
      service.createEntity(createOptions).execute();
    } catch (Exception ex) {
      // Exception is okay if is for Unique Violation
      assertTrue(ex.getLocalizedMessage().startsWith("Unique Violation"));
    }

    try {
      CreateValueOptions createOptions = new CreateValueOptions.Builder(workspaceId, entity, entityValue).build();
      service.createValue(createOptions).execute();
    } catch (Exception ex) {
      // Exception is okay if is for Unique Violation
      assertTrue(ex.getLocalizedMessage().startsWith("Unique Violation"));
    }

    Date start = new Date();

    CreateSynonymOptions createOptions =
        new CreateSynonymOptions.Builder(workspaceId, entity, entityValue, synonym).build();
    service.createSynonym(createOptions).execute();

    try {
      GetSynonymOptions getOptions =
          new GetSynonymOptions.Builder(workspaceId, entity, entityValue, synonym).build();
      Synonym response = service.getSynonym(getOptions).execute();

      assertNotNull(response);
      assertNotNull(response.getSynonym());
      assertEquals(response.getSynonym(), synonym);
      assertNotNull(response.getCreated());
      assertNotNull(response.getUpdated());

      Date now = new Date();
      assertTrue(fuzzyBefore(response.getCreated(), now));
      assertTrue(fuzzyAfter(response.getCreated(), start));
      assertTrue(fuzzyBefore(response.getUpdated(), now));
      assertTrue(fuzzyAfter(response.getUpdated(), start));
    } catch (Exception ex) {
      fail(ex.getMessage());
    } finally {
      DeleteSynonymOptions deleteOptions =
          new DeleteSynonymOptions.Builder(workspaceId, entity, entityValue, synonym).build();
      service.deleteSynonym(deleteOptions).execute();
    }
  }