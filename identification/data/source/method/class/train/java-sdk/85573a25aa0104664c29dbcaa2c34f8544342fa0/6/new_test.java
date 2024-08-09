@Test
  public void testGetIntent() {

    String intentName = "Hello" + UUID.randomUUID().toString(); // gotta be unique
    String intentDescription = "Description of " + intentName;
    String intentExample = "Example of " + intentName;
    List<CreateExample> intentExamples = new ArrayList<CreateExample>();
    intentExamples.add(new CreateExample.Builder().text(intentExample).build());

    Date start = new Date();

    CreateIntentOptions createOptions = new CreateIntentOptions.Builder().workspaceId(workspaceId).intent(intentName)
        .description(intentDescription).examples(intentExamples).build();
    service.createIntent(createOptions).execute();

    try {
      GetIntentOptions getOptions = new GetIntentOptions.Builder()
          .workspaceId(workspaceId)
          .intent(intentName)
          .export(true)
          .includeAudit(true)
          .build();
      IntentExport response = service.getIntent(getOptions).execute();
      assertNotNull(response);
      assertNotNull(response.getIntentName());
      assertEquals(response.getIntentName(), intentName);
      assertNotNull(response.getDescription());
      assertEquals(response.getDescription(), intentDescription);
      assertNotNull(response.getExamples());
      assertNotNull(response.getCreated());
      assertNotNull(response.getUpdated());

      Date now = new Date();
      assertTrue(fuzzyBefore(response.getCreated(), now));
      assertTrue(fuzzyAfter(response.getCreated(), start));
      assertTrue(fuzzyBefore(response.getUpdated(), now));
      assertTrue(fuzzyAfter(response.getUpdated(), start));

      List<Example> examples = response.getExamples();
      assertTrue(examples.size() == 1);
      assertEquals(examples.get(0).getExampleText(), intentExample);
      assertTrue(fuzzyBefore(examples.get(0).getCreated(), now));
      assertTrue(fuzzyAfter(examples.get(0).getCreated(), start));
      assertTrue(fuzzyBefore(examples.get(0).getUpdated(), now));
      assertTrue(fuzzyAfter(examples.get(0).getUpdated(), start));

    } catch (Exception ex) {
      fail(ex.getMessage());
    } finally {
      // Clean up
      DeleteIntentOptions deleteOptions = new DeleteIntentOptions.Builder(workspaceId, intentName).build();
      service.deleteIntent(deleteOptions).execute();
    }
  }