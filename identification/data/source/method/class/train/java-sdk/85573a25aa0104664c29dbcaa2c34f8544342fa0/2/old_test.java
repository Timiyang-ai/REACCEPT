@Test
  public void testGetCounterexample() {

    Date start = new Date();

    String counterExampleText = "Make me a " + UUID.randomUUID().toString() + " sandwich"; // gotta be unique
    CreateCounterexampleOptions createOptions =
            new CreateCounterexampleOptions.Builder(workspaceId, counterExampleText).build();
    service.createCounterexample(createOptions).execute();

    try {
      GetCounterexampleOptions getOptions =
              new GetCounterexampleOptions.Builder(workspaceId, counterExampleText).build();
      Counterexample response = service.getCounterexample(getOptions).execute();
      assertNotNull(response);
      assertNotNull(response.getText());
      assertEquals(response.getText(), counterExampleText);
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
      DeleteCounterexampleOptions deleteOptions =
              new DeleteCounterexampleOptions.Builder(workspaceId, counterExampleText).build();
      service.deleteCounterexample(deleteOptions).execute();
    }
  }