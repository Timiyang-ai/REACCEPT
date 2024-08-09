@Test
  public void testUpdateWorkspace() {

    String workspaceName = "testUpdateWorkspace";
    String workspaceDescription = "Description for testUpdateWorkspace";

    // intents
    CreateIntent intent0 = new CreateIntent.Builder("Hello").build();
    CreateIntent intent1 = new CreateIntent.Builder("Goodbye").build();

    // entities
    CreateEntity entity0 = new CreateEntity.Builder("animal").build();
    CreateEntity entity1 = new CreateEntity.Builder("beverage").build();

    // counterexamples
    CreateCounterexample counterexample0 = new CreateCounterexample.Builder("What are you wearing?").build();
    CreateCounterexample counterexample1 = new CreateCounterexample.Builder("What are you eating?").build();

    CreateWorkspaceOptions createOptions = new CreateWorkspaceOptions.Builder().name(workspaceName)
            .description(workspaceDescription).addIntent(intent0).addIntent(intent1).addEntity(entity0)
            .addEntity(entity1)
            .addCounterexample(counterexample0).addCounterexample(counterexample1).build();

    String workspaceId = null;
    try {
      Workspace createResponse = service.createWorkspace(createOptions).execute();

      assertNotNull(createResponse);
      assertNotNull(createResponse.getWorkspaceId());
      workspaceId = createResponse.getWorkspaceId();

      Date start = new Date();

      String counterExampleText = "What are you drinking";
      CreateCounterexample counterexample2 = new CreateCounterexample.Builder(counterExampleText).build();
      UpdateWorkspaceOptions updateOptions =
              new UpdateWorkspaceOptions.Builder(workspaceId).addCounterexample(counterexample2).build();

      Workspace updateResponse = service.updateWorkspace(updateOptions).execute();

      Date now = new Date();

      assertNotNull(updateResponse.getCreated());
      assertNotNull(updateResponse.getUpdated());
      assertTrue(fuzzyBefore(updateResponse.getCreated(), start));
      assertTrue(fuzzyBefore(updateResponse.getUpdated(), now));
      assertTrue(fuzzyAfter(updateResponse.getUpdated(), start));

      GetCounterexampleOptions getOptions =
              new GetCounterexampleOptions.Builder(workspaceId, counterExampleText).build();
      Counterexample eResponse = service.getCounterexample(getOptions).execute();
      assertNotNull(eResponse);
      assertNotNull(eResponse.getText());
      assertEquals(eResponse.getText(), counterExampleText);

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