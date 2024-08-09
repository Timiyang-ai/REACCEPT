@Test(description = "PUT /vApp/{id}", dependsOnMethods = { "testGetVm" })
   public void testEditVm() {
      Vm newVm = Vm.builder().name(name("new-name-")).description("New Description").build();

      // The method under test
      Task editVm = vmApi.edit(vmUrn, newVm);
      assertTrue(retryTaskSuccess.apply(editVm), String.format(TASK_COMPLETE_TIMELY, "editVm"));

      // Get the edited Vm
      vm = vmApi.get(vmUrn);

      // Check the required fields are set
      assertEquals(vm.getName(), newVm.getName(),
               String.format(OBJ_FIELD_EQ, VM, "Name", newVm.getName(), vm.getName()));
      assertEquals(vm.getDescription(), newVm.getDescription(),
               String.format(OBJ_FIELD_EQ, VM, "Description", newVm.getDescription(), vm.getDescription()));
   }