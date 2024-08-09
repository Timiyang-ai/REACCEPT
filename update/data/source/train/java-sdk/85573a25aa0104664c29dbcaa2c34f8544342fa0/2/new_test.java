@Test
  public void testListDialogNodes() {
    String dialogNodeName = "Test" + UUID.randomUUID().toString();

    try {
      ListDialogNodesOptions listOptions = new ListDialogNodesOptions.Builder(workspaceId).build();
      DialogNodeCollection response = service.listDialogNodes(listOptions).execute();
      assertNotNull(response);
      assertNotNull(response.getDialogNodes());
      assertNotNull(response.getPagination());
      assertNotNull(response.getPagination().getRefreshUrl());
      // nextUrl may be null

      // Now add a dialog node and make sure we get it back
      String dialogNodeDescription = "Description of " + dialogNodeName;

      Date start = new Date();

      CreateDialogNodeOptions createOptions = new CreateDialogNodeOptions.Builder(workspaceId, dialogNodeName)
          .description(dialogNodeDescription)
          .build();
      service.createDialogNode(createOptions).execute();

      long count = response.getDialogNodes().size();
      ListDialogNodesOptions listOptions2 = new ListDialogNodesOptions.Builder(workspaceId)
          .pageLimit(count + 1)
          .includeAudit(true)
          .build();
      DialogNodeCollection response2 = service.listDialogNodes(listOptions2).execute();
      assertNotNull(response2);
      assertNotNull(response2.getDialogNodes());

      List<DialogNode> dialogNodes = response2.getDialogNodes();
      assertTrue(dialogNodes.size() > count);

      DialogNode dialogResponse = null;
      for (DialogNode node : dialogNodes) {
        if (node.getDialogNodeId().equals(dialogNodeName)) {
          dialogResponse = node;
          break;
        }
      }

      assertNotNull(dialogResponse);
      assertNotNull(dialogResponse.getDescription());
      assertEquals(dialogResponse.getDescription(), dialogNodeDescription);

      Date now = new Date();
      assertTrue(fuzzyBefore(dialogResponse.getCreated(), now));
      assertTrue(fuzzyAfter(dialogResponse.getCreated(), start));
      assertTrue(fuzzyBefore(dialogResponse.getUpdated(), now));
      assertTrue(fuzzyAfter(dialogResponse.getUpdated(), start));
    } catch (Exception ex) {
      fail(ex.getMessage());
    } finally {
      // Clean up
      DeleteDialogNodeOptions deleteOptions = new DeleteDialogNodeOptions.Builder(workspaceId, dialogNodeName).build();
      service.deleteDialogNode(deleteOptions).execute();
    }
  }