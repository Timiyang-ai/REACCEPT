  @Test
  public void doDeleteFiles() throws Exception {
    String params = "params";

    doNothing().when( fileResource.fileService ).doDeleteFiles( eq( params ) );

    Response mockResponse = mock( Response.class );
    doReturn( mockResponse ).when( fileResource ).buildOkResponse();

    Response testResponse = fileResource.doDeleteFiles( params );

    verify( fileResource, times( 1 ) ).buildOkResponse();
    verify( fileResource.fileService, times( 1 ) ).doDeleteFiles( params );
    assertEquals( testResponse, mockResponse );
  }