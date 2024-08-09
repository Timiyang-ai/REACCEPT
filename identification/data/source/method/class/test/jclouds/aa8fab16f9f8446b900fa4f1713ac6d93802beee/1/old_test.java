@Test(groups = { "integration", "live" }, singleThreaded = true, dependsOnMethods = "testAddIpPermissionsFromSpec")
   public void testDeleteSecurityGroup() {

      ComputeService computeService = view.getComputeService();

      Optional<SecurityGroupExtension> securityGroupExtension = computeService.getSecurityGroupExtension();
      assertTrue(securityGroupExtension.isPresent(), "security group extension was not present");

      Optional<SecurityGroup> optGroup = getGroup(securityGroupExtension.get());

      assertTrue(optGroup.isPresent());

      SecurityGroup group = optGroup.get();
      assertTrue(securityGroupExtension.get().removeSecurityGroup(group.getId()));
   }