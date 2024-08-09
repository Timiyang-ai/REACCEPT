@Test
  public void testListExamples() {

    createExampleIntent();

    String exampleText = "Howdy " + UUID.randomUUID().toString(); // gotta be unique

    try {
      ListExamplesOptions listOptions = new ListExamplesOptions.Builder(workspaceId, exampleIntent).build();
      ExampleCollection ecResponse = service.listExamples(listOptions).execute();
      assertNotNull(ecResponse);
      assertNotNull(ecResponse.getExamples());
      assertNotNull(ecResponse.getPagination());
      assertNotNull(ecResponse.getPagination().getRefreshUrl());
      // nextUrl may be null

      Date start = new Date();

      // Now add an example and make sure we get it back
      CreateExampleOptions createOptions =
              new CreateExampleOptions.Builder(workspaceId, exampleIntent, exampleText).build();
      service.createExample(createOptions).execute();

      long count = ecResponse.getExamples().size();
      ExampleCollection ecResponse2 =
              service.listExamples(listOptions.newBuilder().pageLimit(count + 1).build()).execute();
      assertNotNull(ecResponse2);
      assertNotNull(ecResponse2.getExamples());

      List<Example> examples = ecResponse2.getExamples();
      assertTrue(examples.size() > count);

      Example exResponse = null;
      for (Example resp : examples) {
        if (resp.getExampleText().equals(exampleText)) {
          exResponse = resp;
          break;
        }
      }

      assertNotNull(exResponse);
      Date now = new Date();
      assertTrue(fuzzyBefore(exResponse.getCreated(), now));
      assertTrue(fuzzyAfter(exResponse.getCreated(), start));
      assertTrue(fuzzyBefore(exResponse.getUpdated(), now));
      assertTrue(fuzzyAfter(exResponse.getUpdated(), start));

    } catch (Exception ex) {
      fail(ex.getMessage());
    } finally {
      // Clean up
      DeleteExampleOptions deleteOptions =
              new DeleteExampleOptions.Builder(workspaceId, exampleIntent, exampleText).build();
      service.deleteExample(deleteOptions).execute();
    }

  }