@Test
  public void testListIntents() {

    String intentName = "Hello" + UUID.randomUUID().toString(); // gotta be unique

    try {
      ListIntentsOptions listOptions = new ListIntentsOptions.Builder(workspaceId).build();
      IntentCollection response = service.listIntents(listOptions).execute();
      assertNotNull(response);
      assertNotNull(response.getIntents());
      assertNotNull(response.getPagination());
      assertNotNull(response.getPagination().getRefreshUrl());
      // nextUrl may be null

      // Now add an intent and make sure we get it back
      String intentDescription = "Description of " + intentName;
      String intentExample = "Example of " + intentName;
      List<CreateExample> intentExamples = new ArrayList<CreateExample>();
      intentExamples.add(new CreateExample.Builder().text(intentExample).build());

      Date start = new Date();

      CreateIntentOptions createOptions = new CreateIntentOptions.Builder(workspaceId, intentName)
              .description(intentDescription).examples(intentExamples).build();
      service.createIntent(createOptions).execute();

      long count = response.getIntents().size();
      ListIntentsOptions listOptions2 =
              new ListIntentsOptions.Builder(workspaceId).export(true).pageLimit(count + 1).build();
      IntentCollection response2 = service.listIntents(listOptions2).execute();
      assertNotNull(response2);
      assertNotNull(response2.getIntents());

      List<IntentExport> intents = response2.getIntents();
      assertTrue(intents.size() > count);

      IntentExport ieResponse = null;
      for (IntentExport resp : intents) {
        if (resp.getIntentName().equals(intentName)) {
          ieResponse = resp;
          break;
        }
      }

      assertNotNull(ieResponse);
      assertNotNull(ieResponse.getDescription());
      assertEquals(ieResponse.getDescription(), intentDescription);
      assertNotNull(ieResponse.getExamples());
      assertTrue(ieResponse.getExamples().size() == 1);
      assertEquals(ieResponse.getExamples().get(0).getExampleText(), intentExample);

      Date now = new Date();
      assertTrue(fuzzyBefore(ieResponse.getCreated(), now));
      assertTrue(fuzzyAfter(ieResponse.getCreated(), start));
      assertTrue(fuzzyBefore(ieResponse.getUpdated(), now));
      assertTrue(fuzzyAfter(ieResponse.getUpdated(), start));

    } catch (Exception ex) {
      fail(ex.getMessage());
    } finally {
      // Clean up
      DeleteIntentOptions deleteOptions = new DeleteIntentOptions.Builder(workspaceId, intentName).build();
      service.deleteIntent(deleteOptions).execute();
    }
  }