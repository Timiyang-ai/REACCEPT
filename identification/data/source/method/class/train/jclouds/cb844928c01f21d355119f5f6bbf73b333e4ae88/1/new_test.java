@Test(description = "PUT /admin/vdc/{id}", enabled = false)
   public void testEditVdc() throws Exception {
      String origName = lazyGetVdc().getName();
      String newName = name("a");
      Exception exception = null;

      AdminVdc vdc = AdminVdc.builder().name(newName).build();

      try {
         Task task = vdcApi.update(vdcUrn, vdc);
         assertTaskSucceeds(task);

         AdminVdc modified = vdcApi.get(vdcUrn);
         assertEquals(modified.getName(), newName);

         // parent type
         Checks.checkAdminVdc(vdc);
      } catch (Exception e) {
         exception = e;
      } finally {
         try {
            AdminVdc restorableVdc = AdminVdc.builder().name(origName).build();
            Task task = vdcApi.update(vdcUrn, restorableVdc);
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