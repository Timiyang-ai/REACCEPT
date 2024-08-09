   @Test
   public void testautoAssignFloatingIp() {
      NovaTemplateOptions options = new NovaTemplateOptions().autoAssignFloatingIp(true);
      assert options.shouldAutoAssignFloatingIp();
   }