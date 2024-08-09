@Test(description = "PUT /admin/vdc/{id}", enabled=false)
   public void testEditVdc() throws Exception {
      String origName = vdcApi.getVdc(adminVdcUri).getName();
      String newName = name("a");
      Exception exception = null;
      
      AdminVdc vdc = AdminVdc.builder()
               .name(newName)
               .build();
      
      try {
         Task task = vdcApi.editVdc(adminVdcUri, vdc);
         assertTaskSucceeds(task);
         
         AdminVdc modified = vdcApi.getVdc(adminVdcUri);
         assertEquals(modified.getName(), newName);
          
         // parent type
         Checks.checkAdminVdc(vdc);
      } catch (Exception e) {
         exception = e;
      } finally {
         try {
            AdminVdc restorableVdc = AdminVdc.builder().name(origName).build();
            Task task = vdcApi.editVdc(adminVdcUri, restorableVdc);
            assertTaskSucceeds(task);
         } catch (Exception e) {
            if (exception != null) {
               logger.warn(e, "Error resetting adminVdc.name; rethrowing original test exception...");
               throw exception;
            } else {
               throw e;
            }
         }
      }
   }