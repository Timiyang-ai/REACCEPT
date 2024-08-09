  @Test
  public void test_appliedDropsFor_when_matchesSome_then_removesMatched() throws Exception {

    PendingDrops pendingDrops = new PendingDrops();

    DropColumn one = col("one");
    DropColumn two = col("two");
    pendingDrops.add(V1_1, changeSet(one, two));
    pendingDrops.add(V1_1, changeSet("three", "four"));
    assertThat(pendingDrops.testGetEntryFor(V1_1).list).asList().hasSize(2);

    ChangeSet applied = changeSet("two");
    applied.setDropsFor("1.1");

    assertThat(pendingDrops.appliedDropsFor(applied)).isFalse();
    assertThat(pendingDrops.testGetEntryFor(V1_1).list).asList().hasSize(2);
    assertThat(pendingDrops.testGetEntryFor(V1_1).list.get(0).getChangeSetChildren()).asList().containsExactly(one);
  }