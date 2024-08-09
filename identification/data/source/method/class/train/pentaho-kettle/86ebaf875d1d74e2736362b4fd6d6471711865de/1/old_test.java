  @Test
  public void writeToFileTest() throws KettleException, IOException {
    doCallRealMethod().when( sharedObjectsMock ).writeToFile( any( FileObject.class ), anyString() );

    when( sharedObjectsMock.initOutputStreamUsingKettleVFS( any( FileObject.class ) ) ).thenThrow(
        new RuntimeException() );

    try {
      sharedObjectsMock.writeToFile( any( FileObject.class ), anyString() );
    } catch ( KettleException e ) {
      // NOP: catch block throws an KettleException after calling sharedObjectsMock method
    }

    // check if file restored in case of exception is occurred
    verify( sharedObjectsMock ).restoreFileFromBackup( anyString() );
  }