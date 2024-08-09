  @Test
  public void test_compare_addColumnDropColumn() throws Exception {

    ModelDiff diff = new ModelDiff();
    diff.compareTables(base(), newTable());

    List<Object> createChanges = diff.getApplyChanges();
    assertThat(createChanges).hasSize(1);
    AddColumn addColumn = (AddColumn) createChanges.get(0);
    assertThat(addColumn.getColumn()).extracting("name").contains("comment");
    assertThat(addColumn.getColumn()).extracting("type").contains("varchar(1000)");

    List<Object> dropChanges = diff.getDropChanges();
    assertThat(dropChanges).hasSize(1);

    DropColumn dropColumn = (DropColumn) dropChanges.get(0);
    assertThat(dropColumn.getColumnName()).isEqualTo("status");
    assertThat(dropColumn.getTableName()).isEqualTo("tab");
  }