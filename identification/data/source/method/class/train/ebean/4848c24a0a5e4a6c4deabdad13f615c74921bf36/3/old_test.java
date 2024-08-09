  @Test
  public void test_pendingDrops() throws Exception {

    PendingDrops pendingDrops = new PendingDrops();
    assertThat(pendingDrops.pendingDrops()).isEmpty();

    pendingDrops.add(V1_1, new ChangeSet());
    pendingDrops.add(V1_2, new ChangeSet());
    assertThat(pendingDrops.pendingDrops()).containsExactly("1.1", "1.2");
  }