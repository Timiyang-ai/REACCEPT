@Test
  public void testUpdateDialogNode() {
    String dialogNodeName = "Test" + UUID.randomUUID().toString();
    String dialogNodeDescription = "Description of " + dialogNodeName;

    CreateDialogNodeOptions createOptions = new CreateDialogNodeOptions.Builder(workspaceId, dialogNodeName)
        .description(dialogNodeDescription).build();
    service.createDialogNode(createOptions).execute();

    String dialogNodeName2 = "Test2" + UUID.randomUUID().toString();

    try {
      String dialogNodeDescription2 = "Updated description of " + dialogNodeName;
      UpdateDialogNodeOptions updateOptions = new UpdateDialogNodeOptions.Builder()
          .workspaceId(workspaceId)
          .dialogNode(dialogNodeName)
          .newDialogNode(dialogNodeName2)
          .newDescription(dialogNodeDescription2)
          .build();
      DialogNode response = service.updateDialogNode(updateOptions).execute();
      assertNotNull(response);
      assertNotNull(response.getDialogNodeId());
      assertEquals(response.getDialogNodeId(), dialogNodeName2);
      assertNotNull(response.getDescription());
      assertEquals(response.getDescription(), dialogNodeDescription2);
    } catch (Exception ex) {
      fail(ex.getMessage());
    } finally {
      // Clean up
      DeleteDialogNodeOptions deleteOptions = new DeleteDialogNodeOptions.Builder(workspaceId, dialogNodeName2).build();
      service.deleteDialogNode(deleteOptions).execute();
    }
  }