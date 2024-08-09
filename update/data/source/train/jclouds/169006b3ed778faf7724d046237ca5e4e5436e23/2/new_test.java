@Test(description = "GET /vApp/{id}/question", dependsOnMethods = { "testDeployVm" })
   public void testGetPendingQuestion() {
      // Power on Vm
      vm = powerOnVm(vmUrn);

      // TODO how to test?

      // The method under test
      VmPendingQuestion question = vmApi.getPendingQuestion(vmUrn);

      // Check the retrieved object is well formed
      checkVmPendingQuestion(question);
   }