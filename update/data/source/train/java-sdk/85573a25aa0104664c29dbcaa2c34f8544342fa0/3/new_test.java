@Test
  public void testGetExample() {

    createExampleIntent();

    Date start = new Date();

    String exampleText = "Howdy " + UUID.randomUUID().toString(); // gotta be unique
    CreateExampleOptions createOptions = new CreateExampleOptions.Builder(workspaceId, exampleIntent, exampleText)
        .build();
    service.createExample(createOptions).execute();

    try {
      GetExampleOptions getOptions = new GetExampleOptions.Builder(workspaceId, exampleIntent, exampleText)
          .includeAudit(true)
          .build();
      Example response = service.getExample(getOptions).execute();
      assertNotNull(response);
      assertNotNull(response.getExampleText());
      assertEquals(response.getExampleText(), exampleText);
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
      // Clean up
      DeleteExampleOptions deleteOptions = new DeleteExampleOptions.Builder(workspaceId, exampleIntent, exampleText)
          .build();
      service.deleteExample(deleteOptions).execute();
    }
  }