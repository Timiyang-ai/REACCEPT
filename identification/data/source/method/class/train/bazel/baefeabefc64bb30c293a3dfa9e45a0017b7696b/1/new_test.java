  @Test
  public void getCompressedDirectDepsForDoneEntry() throws InterruptedException {
    InMemoryNodeEntry entry = new InMemoryNodeEntry();
    ImmutableList<ImmutableSet<SkyKey>> groupedDirectDeps = ImmutableList.of(
        ImmutableSet.of(key("1A")),
        ImmutableSet.of(key("2A"), key("2B")),
        ImmutableSet.of(key("3A"), key("3B"), key("3C")),
        ImmutableSet.of(key("4A"), key("4B"), key("4C"), key("4D")));
    assertThatNodeEntry(entry)
        .addReverseDepAndCheckIfDone(null)
        .isEqualTo(DependencyState.NEEDS_SCHEDULING);
    entry.markRebuilding();
    for (Set<SkyKey> depGroup : groupedDirectDeps) {
      GroupedListHelper<SkyKey> helper = new GroupedListHelper<>();
      helper.startGroup();
      for (SkyKey item : depGroup) {
        helper.add(item);
      }
      helper.endGroup();

      entry.addTemporaryDirectDeps(helper);
      for (SkyKey dep : depGroup) {
        entry.signalDep(ZERO_VERSION, dep);
      }
    }
    entry.setValue(new IntegerValue(42), IntVersion.of(42L));
    assertThat(entry.getNumberOfDirectDepGroups()).isEqualTo(groupedDirectDeps.size());
    int i = 0;
    GroupedList<SkyKey> entryGroupedDirectDeps =
        GroupedList.create(entry.getCompressedDirectDepsForDoneEntry());
    assertThat(Iterables.size(entryGroupedDirectDeps)).isEqualTo(groupedDirectDeps.size());
    for (Iterable<SkyKey> depGroup : entryGroupedDirectDeps) {
      assertThat(depGroup).containsExactlyElementsIn(groupedDirectDeps.get(i++));
    }
  }