  @Test
  public void setSelectName() {
    selectValuesMeta.setSelectName( new String[] { FIRST_FIELD, SECOND_FIELD } );
    assertArrayEquals( new String[] { FIRST_FIELD, SECOND_FIELD }, selectValuesMeta.getSelectName() );
  }