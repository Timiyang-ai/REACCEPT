@Test(testName = "GET /vApp/{id}")
   public void testGetVApp() {
      VApp vAppInstantiated = instantiateVApp();

      // The method under test
      vApp = vAppClient.getVApp(vAppInstantiated.getHref());

      // Check the retrieved object is well formed
      checkVApp(vApp);

      // Check that task progress is increasing
      Integer vAppProgress = Iterables.getOnlyElement(vApp.getTasks()).getProgress();
      Integer vAppInstantiatedProgress = Iterables.getOnlyElement(vAppInstantiated.getTasks()).getProgress();
      assertTrue(vAppProgress >= vAppInstantiatedProgress,
            String.format(ENTITY_CONDITION, VAPP, "have an increasing value in the Progress field", String.format("%d < %d", vAppProgress, vAppInstantiatedProgress)));

      // Cheat and copy the VApp with the progress of the instantiate task modified for equality testing
      VApp vAppCopy = vApp.toBuilder().tasks(ImmutableSet.of(Iterables.getOnlyElement(vApp.getTasks()).toBuilder().progress(vAppInstantiatedProgress).build())).build();
      assertEquals(vAppCopy, vAppInstantiated, String.format(ENTITY_EQUAL, VAPP));
   }