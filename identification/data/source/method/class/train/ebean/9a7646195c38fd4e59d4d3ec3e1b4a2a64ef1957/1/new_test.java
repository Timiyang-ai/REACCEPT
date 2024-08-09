  @Test
  public void test_dropTable() {

    MTable base = base();
    DropTable dropTable = base.dropTable();
    assertThat(dropTable.getName()).isEqualTo(base.getName());
  }