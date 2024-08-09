  @Test //PDI-16459
  public void getFieldsTest() throws KettleException {
    String name = "name";
    SalesforceConnection conn = new SalesforceConnection( null, "http://localhost:1234", "aUser", "aPass" );
    Field[] fields = new Field[ 1 ];
    Field field = new Field();
    field.setRelationshipName( "Parent" );
    field.setName( name );
    fields[ 0 ] = field;
    String[] names = conn.getFields( fields );
    Assert.assertEquals( name, names[ 0 ] );
  }