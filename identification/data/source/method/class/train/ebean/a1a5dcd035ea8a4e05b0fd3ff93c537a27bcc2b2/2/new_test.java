  @Test
  public void test_apply_dropColumn() {

    MTable base = base();

    DropColumn dropColumn = new DropColumn();
    dropColumn.setTableName("tab");
    dropColumn.setColumnName("name");

    base.apply(dropColumn);
    assertThat(base.getColumn("name")).isNull();
  }