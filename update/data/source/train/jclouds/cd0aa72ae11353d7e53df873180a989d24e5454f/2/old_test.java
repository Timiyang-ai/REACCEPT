@Test(description = "PUT /vApp/{id}", dependsOnMethods = { "testGetVm" })
   public void testModifyVm() {
      Vm newVm = Vm.builder()
            .name(name("new-name-"))
            .description("New Description")
            .build();

      // The method under test
      Task modifyVm = vmApi.modifyVm(vm.getHref(), newVm);
      assertTrue(retryTaskSuccess.apply(modifyVm), String.format(TASK_COMPLETE_TIMELY, "modifyVm"));

      // Get the updated Vm
      vm = vmApi.getVm(vm.getHref());

      // Check the required fields are set
      assertEquals(vm.getName(), newVm.getName(), String.format(OBJ_FIELD_EQ, VM, "Name", newVm.getName(), vm.getName()));
      assertEquals(vm.getDescription(), newVm.getDescription(), String.format(OBJ_FIELD_EQ, VM, "Description", newVm.getDescription(), vm.getDescription()));
   }