@Test
  public void testListCounterexamples() {

    String counterExampleText = "Make me a " + UUID.randomUUID().toString() + " sandwich"; // gotta be unique

    try {
      ListCounterexamplesOptions listOptions = new ListCounterexamplesOptions.Builder(workspaceId).build();
      CounterexampleCollection ccResponse = service.listCounterexamples(listOptions).execute();
      assertNotNull(ccResponse);
      assertNotNull(ccResponse.getCounterexamples());
      assertNotNull(ccResponse.getPagination());
      assertNotNull(ccResponse.getPagination().getRefreshUrl());
      // nextUrl may be null

      Date start = new Date();

      // Now add a counterexample and make sure we get it back
      CreateCounterexampleOptions createOptions =
              new CreateCounterexampleOptions.Builder(workspaceId, counterExampleText).build();
      service.createCounterexample(createOptions).execute();

      long count = ccResponse.getCounterexamples().size();
      CounterexampleCollection ccResponse2 =
              service.listCounterexamples(listOptions.newBuilder().pageLimit(count + 1).build()).execute();
      assertNotNull(ccResponse2);
      assertNotNull(ccResponse2.getCounterexamples());

      List<Counterexample> counterexamples = ccResponse2.getCounterexamples();
      assertTrue(counterexamples.size() > count);

      Counterexample exResponse = null;
      for (Counterexample resp : counterexamples) {
        if (resp.getText().equals(counterExampleText)) {
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
      try {
        DeleteCounterexampleOptions deleteOptions =
                new DeleteCounterexampleOptions.Builder(workspaceId, counterExampleText).build();
        service.deleteCounterexample(deleteOptions).execute();
      } catch (NotFoundException ex) {
        // Okay
      }
    }
  }