@Test(description = "DELETE /vApp/{id}")
   public void testRemoveVApp() {
      // Create a temporary VApp to remove
      VApp temp = instantiateVApp();

      // The method under test
      Task removeVApp = vAppApi.remove(temp.getId());
      assertTrue(retryTaskSuccess.apply(removeVApp), String.format(TASK_COMPLETE_TIMELY, "removeVApp"));

      VApp removed = vAppApi.get(temp.getId());
      assertNull(removed, "The VApp " + temp.getName() + " should have been removed");
   }