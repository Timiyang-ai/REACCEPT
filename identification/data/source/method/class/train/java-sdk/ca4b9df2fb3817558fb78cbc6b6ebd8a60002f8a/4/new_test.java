@Test
  public void testGetWorkspace() {

    GetWorkspaceOptions getOptions = new GetWorkspaceOptions.Builder(workspaceId).export(false).build();
    WorkspaceExport response = service.getWorkspace(getOptions).execute();

    try {
      assertNotNull(response);
      assertNotNull(response.getWorkspaceId());
      assertEquals(response.getWorkspaceId(), workspaceId);
      assertNotNull(response.getName());
      assertNotNull(response.getDescription());
      assertNotNull(response.getLanguage());

      Date now = new Date();

      assertNotNull(response.getCreated());
      assertNotNull(response.getUpdated());
      assertTrue(fuzzyBefore(response.getCreated(), now));
      assertTrue(fuzzyBefore(response.getUpdated(), now));

      // metadata, intents, entities, dialogNodes, and counterexamples could be null

    } catch (Exception ex) {
      fail(ex.getMessage());
    }
  }