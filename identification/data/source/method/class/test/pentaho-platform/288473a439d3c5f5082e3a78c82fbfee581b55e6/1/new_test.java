  @Test
  public void writeFileWithEncodedName_DecodesPathAndName() throws Exception {
    final String originalFile = "my-\"quoted\".ktr";
    final String originalPath = "/public/" + originalFile;

    doReturn( false ).when( repositoryPublishResource ).invalidPath( originalPath );

    repositoryPublishResource.writeFileWithEncodedName(
      encode( originalPath ), emptyStream(), true, dummyInfo( originalFile ) );

    // decodes path
    verify( repositoryPublishResource.repositoryPublishService, times( 1 ) ).publishFile( eq( originalPath ), any( InputStream.class ), any( Optional.class ) );
  }