@Test(description = "GET /vApp/{id}/question", dependsOnMethods = { "testDeployVm" })
   public void testGetPendingQuestion() {
      // Power on Vm
      vm = powerOnVm(vm.getHref());

      // TODO how to test?

      // The method under test
      VmPendingQuestion question = vmApi.getPendingQuestion(vm.getHref());

      // Check the retrieved object is well formed
      checkVmPendingQuestion(question);
   }