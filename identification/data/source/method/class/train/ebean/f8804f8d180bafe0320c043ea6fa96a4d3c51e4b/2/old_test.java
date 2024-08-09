  @Test
  public void test_registerPendingHistoryDropColumns() throws Exception {

    TDModelContainer modelContainer = new TDModelContainer();

    DropColumn drop1 = col("one");
    drop1.setWithHistory(Boolean.TRUE);

    DropColumn drop2 = col("two");

    ChangeSet changeSet = changeSet(drop1, drop2);

    PendingDrops pendingDrops = new PendingDrops();
    pendingDrops.add(V1_1, changeSet);
    pendingDrops.registerPendingHistoryDropColumns(modelContainer);

    assertThat(modelContainer.drops).containsExactly(changeSet);
  }