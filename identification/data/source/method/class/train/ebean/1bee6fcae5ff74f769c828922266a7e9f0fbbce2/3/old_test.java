  @Test
  public void apply_sql() {
    ModelContainer container = new ModelContainer();
    container.apply(mig("3.0__rawSql.model.xml"), ver("3.0"));
    assertThat(container.getPendingDrops()).isEmpty();
  }