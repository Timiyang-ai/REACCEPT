  @Test
  public void getColumnFromProperty() {

    String fkCol = "bridgetab_user_id";

    String col = namingConvention.getColumnFromProperty(null, fkCol);
    assertThat(col).isEqualTo(fkCol);
  }