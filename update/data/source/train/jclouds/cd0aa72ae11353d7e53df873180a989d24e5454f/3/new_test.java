@Test(description = "DELETE /vApp/{id}")
   public void testRemoveVApp() {
      // Create a temporary VApp to remove
      VApp temp = instantiateVApp();

      // The method under test
      Task removeVApp = vAppApi.removeVApp(temp.getHref());
      assertTrue(retryTaskSuccess.apply(removeVApp), String.format(TASK_COMPLETE_TIMELY, "removeVApp"));

      VApp removed = vAppApi.getVApp(temp.getHref());
      assertNull(removed, "The VApp " + temp.getName() + " should have been removed");
   }