@Test(description = "DELETE /admin/vdc/{id}", enabled=false)
   public void testDeleteVdc() throws Exception {
      // TODO Need to have a VDC that we're happy to delete!
      Task task = vdcApi.deleteVdc(adminVdcUri);
      assertTaskSucceeds(task);
         
      try {
         vdcApi.getVdc(adminVdcUri);
      } catch (VCloudDirectorException e) {
         // success; unreachable because it has been deleted
      }
   }