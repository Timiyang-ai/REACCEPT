  @Test
  public void setFieldValueTest() {
    Field field = new Field();
    System.setProperty( Const.KETTLE_EMPTY_STRING_DIFFERS_FROM_NULL, "N" );
    field.setFieldValue( "theValue" );
    assertEquals( "theValue", field.getFieldValue() );
  }