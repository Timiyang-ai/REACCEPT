  @Test
  public void test_migrationForVersion() throws Exception {

    PendingDrops pendingDrops = new PendingDrops();

    ChangeSet applyDropChangeSet1 = new ChangeSet();
    ChangeSet applyDropChangeSet2 = new ChangeSet();

    MigrationVersion version = V1_1;
    pendingDrops.add(version, applyDropChangeSet1);
    pendingDrops.add(version, applyDropChangeSet2);

    Migration migration = pendingDrops.migrationForVersion("1_1");
    assertThat(migration.getChangeSet()).containsExactly(applyDropChangeSet1, applyDropChangeSet2);

    assertThat(pendingDrops.testContainsEntryFor(version)).isFalse();
  }