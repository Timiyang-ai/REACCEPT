  @Test
  public void getBlockLocations() {
    mInfo.addWorker(1, "MEM");
    mInfo.addWorker(2, "MEM");
    mInfo.addWorker(3, "HDD");

    List<MasterBlockLocation> locations = mInfo.getBlockLocations();
    Set<MasterBlockLocation> expectedLocations = ImmutableSet.of(
        new MasterBlockLocation(1, "MEM"),
        new MasterBlockLocation(2, "MEM"),
        new MasterBlockLocation(3, "HDD"));
    assertEquals(expectedLocations, ImmutableSet.copyOf(locations));
  }