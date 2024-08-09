@Test(description = "GET /vApp/{id}")
   public void testGetVm() {
      // The method under test
      vm = vmApi.get(vmUrn);

      // Check the retrieved object is well formed
      checkVm(vm);

      // Check the required fields are set
      assertEquals(vm.isDeployed(), Boolean.FALSE,
               String.format(OBJ_FIELD_EQ, VM, "deployed", "FALSE", vm.isDeployed().toString()));

      // Check status
      assertVmStatus(vmUrn, Status.POWERED_OFF);
   }