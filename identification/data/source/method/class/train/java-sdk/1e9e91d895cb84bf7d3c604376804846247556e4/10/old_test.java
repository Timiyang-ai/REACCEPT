@Test
  public void testGetValue() {

    String entity = "beverage";
    String entityValue = "coffee" + UUID.randomUUID().toString();
    String synonym1 = "java";
    String synonym2 = "joe";

    try {
      CreateEntityOptions createOptions = new CreateEntityOptions.Builder(workspaceId, entity).build();
      service.createEntity(createOptions).execute();
    } catch (Exception ex) {
      // Exception is okay if is for Unique Violation
      assertTrue(ex.getLocalizedMessage().startsWith("Unique Violation"));
    }

    CreateValueOptions createOptions = new CreateValueOptions.Builder(workspaceId, entity, entityValue)
        .synonyms(new ArrayList<String>(Arrays.asList(synonym1, synonym2)))
        .build();
    service.createValue(createOptions).execute();

    Date start = new Date();

    try {
      GetValueOptions getOptions =
          new GetValueOptions.Builder(workspaceId, entity, entityValue)
              .export(true)
              .build();
      ValueExport response = service.getValue(getOptions).execute();

      assertNotNull(response);
      assertNotNull(response.getValue());
      assertEquals(response.getValue(), entityValue);
      assertNotNull(response.getCreated());
      assertNotNull(response.getUpdated());

      Date now = new Date();
      assertTrue(fuzzyBefore(response.getCreated(), now));
      assertTrue(fuzzyAfter(response.getCreated(), start));
      assertTrue(fuzzyBefore(response.getUpdated(), now));
      assertTrue(fuzzyAfter(response.getUpdated(), start));

      assertNotNull(response.getSynonyms());
      assertTrue(response.getSynonyms().size() == 2);
      assertTrue(response.getSynonyms().contains(synonym1));
      assertTrue(response.getSynonyms().contains(synonym2));
    } catch (Exception ex) {
      fail(ex.getMessage());
    } finally {
      DeleteValueOptions deleteOptions =
          new DeleteValueOptions.Builder(workspaceId, entity, entityValue).build();
      service.deleteValue(deleteOptions).execute();
    }
  }