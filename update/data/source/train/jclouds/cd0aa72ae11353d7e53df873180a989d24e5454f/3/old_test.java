@Test(description = "DELETE /vApp/{id}")
   public void testDeleteVApp() {
      // Create a temporary VApp to delete
      VApp temp = instantiateVApp();

      // The method under test
      Task deleteVApp = vAppApi.deleteVApp(temp.getHref());
      assertTrue(retryTaskSuccess.apply(deleteVApp), String.format(TASK_COMPLETE_TIMELY, "deleteVApp"));

      VApp deleted = vAppApi.getVApp(temp.getHref());
      assertNull(deleted, "The VApp "+temp.getName()+" should have been deleted");
   }