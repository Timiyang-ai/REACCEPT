  @Test
  public void doPostImport() throws ParseException, PlatformImportException {
    RepositoryImportResource importResource = new RepositoryImportResource();
    InputStream mockInputStream = mock( InputStream.class );
    FormDataContentDisposition formDataContentDisposition =  mock( FormDataContentDisposition.class );
    when( policy.isAllowed( anyString() ) ).thenAnswer( new Answer<Boolean>() {
      @Override
      public Boolean answer( InvocationOnMock invocation ) throws Throwable {
        return true;
      }
    } );
    importResource.doPostImport( IMPORT_DIR, mockInputStream, "true", "true", "true", "true", "UTF-8", "WARN", formDataContentDisposition, "" );
    Assert.assertNull( ImportSession.getSession().getManifest()  );
  }