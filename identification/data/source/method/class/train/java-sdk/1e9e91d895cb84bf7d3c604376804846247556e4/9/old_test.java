@Test
  public void testCreateDialogNode() {
    String dialogNodeName = "Test" + UUID.randomUUID().toString();
    String dialogNodeDescription = "Description of " + dialogNodeName;

    Date start = new Date();

    CreateDialogNodeOptions createOptions =
        new CreateDialogNodeOptions.Builder(workspaceId, dialogNodeName).description(dialogNodeDescription).build();
    DialogNode response = service.createDialogNode(createOptions).execute();

    try {
      assertNotNull(response);
      assertNotNull(response.getDialogNode());
      assertEquals(response.getDialogNode(), dialogNodeName);
      assertNotNull(response.getDescription());
      assertEquals(response.getDescription(), dialogNodeDescription);
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
      DeleteDialogNodeOptions deleteOptions = new DeleteDialogNodeOptions.Builder(workspaceId, dialogNodeName).build();
      service.deleteDialogNode(deleteOptions).execute();
    }
  }