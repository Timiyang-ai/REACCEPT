@Test
  public void testCreateWorkspace() {

    String workspaceName = "API Test " + UUID.randomUUID().toString(); // gotta be unique
    String workspaceDescription = "Description of " + workspaceName;
    String workspaceLanguage = "en";

    // metadata
    Map<String, Object> workspaceMetadata = new HashMap<String, Object>();
    String metadataValue = "value for " + workspaceName;
    workspaceMetadata.put("key", metadataValue);

    // intents
    List<CreateIntent> workspaceIntents = new ArrayList<CreateIntent>();
    String intentName = "Hello" + UUID.randomUUID().toString(); // gotta be unique
    String intentDescription = "Description of " + intentName;
    String intentExample = "Example of " + intentName;
    List<CreateExample> intentExamples = new ArrayList<CreateExample>();
    intentExamples.add(new CreateExample.Builder().text(intentExample).build());
    workspaceIntents.add(
        new CreateIntent.Builder().intent(intentName).description(intentDescription).examples(intentExamples).build());

    // entities
    List<CreateEntity> workspaceEntities = new ArrayList<CreateEntity>();
    String entityName = "Hello" + UUID.randomUUID().toString(); // gotta be unique
    String entityDescription = "Description of " + entityName;
    String entitySource = "Source for " + entityName;
    String entityValue = "Value of " + entityName;
    String entityValueSynonym = "Synonym for Value of " + entityName;
    List<CreateValue> entityValues = new ArrayList<CreateValue>();
    entityValues.add(new CreateValue.Builder().value(entityValue).addSynonym(entityValueSynonym).build());
    workspaceEntities
        .add(new CreateEntity.Builder().entity(entityName).description(entityDescription).values(entityValues).build());

    // counterexamples
    List<CreateCounterexample> workspaceCounterExamples = new ArrayList<CreateCounterexample>();
    String counterExampleText = "Counterexample for " + workspaceName;
    workspaceCounterExamples.add(new CreateCounterexample.Builder().text(counterExampleText).build());

    CreateWorkspaceOptions createOptions = new CreateWorkspaceOptions.Builder().name(workspaceName)
        .description(workspaceDescription).language(workspaceLanguage).metadata(workspaceMetadata)
        .intents(workspaceIntents).entities(workspaceEntities).counterexamples(workspaceCounterExamples).build();

    String workspaceId = null;
    try {
      Date start = new Date();

      Workspace response = service.createWorkspace(createOptions).execute();

      assertNotNull(response);
      assertNotNull(response.getWorkspaceId());
      workspaceId = response.getWorkspaceId();
      assertNotNull(response.getName());
      assertEquals(response.getName(), workspaceName);
      assertNotNull(response.getDescription());
      assertEquals(response.getDescription(), workspaceDescription);
      assertNotNull(response.getLanguage());
      assertEquals(response.getLanguage(), workspaceLanguage);

      Date now = new Date();

      assertNotNull(response.getCreated());
      assertNotNull(response.getUpdated());
      assertTrue(fuzzyBefore(response.getCreated(), now));
      assertTrue(fuzzyAfter(response.getCreated(), start));
      assertTrue(fuzzyBefore(response.getUpdated(), now));
      assertTrue(fuzzyAfter(response.getUpdated(), start));

      // metadata
      assertNotNull(response.getMetadata());
      assertNotNull(response.getMetadata().get("key"));
      assertEquals(response.getMetadata().get("key"), metadataValue);

      GetWorkspaceOptions getOptions = new GetWorkspaceOptions.Builder(workspaceId).export(true).build();
      WorkspaceExport exResponse = service.getWorkspace(getOptions).execute();
      assertNotNull(exResponse);

      // intents
      assertNotNull(exResponse.getIntents());
      assertTrue(exResponse.getIntents().size() == 1);
      assertNotNull(exResponse.getIntents().get(0).getIntent());
      assertEquals(exResponse.getIntents().get(0).getIntent(), intentName);
      assertNotNull(exResponse.getIntents().get(0).getDescription());
      assertEquals(exResponse.getIntents().get(0).getDescription(), intentDescription);
      assertNotNull(exResponse.getIntents().get(0).getExamples());
      assertTrue(exResponse.getIntents().get(0).getExamples().size() == 1);
      assertNotNull(exResponse.getIntents().get(0).getExamples().get(0));
      assertNotNull(exResponse.getIntents().get(0).getExamples().get(0).getText());
      assertEquals(exResponse.getIntents().get(0).getExamples().get(0).getText(), intentExample);

      // entities
      assertNotNull(exResponse.getEntities());
      assertTrue(exResponse.getEntities().size() == 1);
      assertNotNull(exResponse.getEntities().get(0).getEntity());
      assertEquals(exResponse.getEntities().get(0).getEntity(), entityName);
      assertNotNull(exResponse.getEntities().get(0).getDescription());
      assertEquals(exResponse.getEntities().get(0).getDescription(), entityDescription);
      assertNotNull(exResponse.getEntities().get(0).getValues());
      assertTrue(exResponse.getEntities().get(0).getValues().size() == 1);
      assertNotNull(exResponse.getEntities().get(0).getValues().get(0).getValue());
      assertEquals(exResponse.getEntities().get(0).getValues().get(0).getValue(), entityValue);
      assertNotNull(exResponse.getEntities().get(0).getValues().get(0).getSynonyms());
      assertTrue(exResponse.getEntities().get(0).getValues().get(0).getSynonyms().size() == 1);
      assertEquals(exResponse.getEntities().get(0).getValues().get(0).getSynonyms().get(0), entityValueSynonym);

      // counterexamples
      assertNotNull(exResponse.getCounterexamples());
      assertTrue(exResponse.getCounterexamples().size() == 1);
      assertNotNull(exResponse.getCounterexamples().get(0).getText());
      assertEquals(exResponse.getCounterexamples().get(0).getText(), counterExampleText);

    } catch (Exception ex) {
      fail(ex.getMessage());
    } finally {
      // Clean up
      if (workspaceId != null) {
        DeleteWorkspaceOptions deleteOptions = new DeleteWorkspaceOptions.Builder(workspaceId).build();
        service.deleteWorkspace(deleteOptions).execute();
      }
    }
  }