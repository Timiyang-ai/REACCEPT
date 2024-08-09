  @Test
  public void doGetMetadata() {
    String pathId = "path:to:file:file1.ext";

    List<StringKeyStringValueDto> stringKeyStringValueDtos = new ArrayList<StringKeyStringValueDto>();
    StringKeyStringValueDto stringKeyStringValueDto1 = mock( StringKeyStringValueDto.class );
    doReturn( "key1" ).when( stringKeyStringValueDto1 ).getKey();
    doReturn( "value1" ).when( stringKeyStringValueDto1 ).getValue();

    StringKeyStringValueDto stringKeyStringValueDto2 = mock( StringKeyStringValueDto.class );
    doReturn( "key2" ).when( stringKeyStringValueDto2 ).getKey();
    doReturn( "value2" ).when( stringKeyStringValueDto2 ).getValue();

    stringKeyStringValueDtos.add( stringKeyStringValueDto1 );
    stringKeyStringValueDtos.add( stringKeyStringValueDto2 );

    doReturn( "/path/to/file/file1.ext" ).when( fileService ).idToPath( pathId );

    RepositoryFileDto repositoryFileDto = mock( RepositoryFileDto.class );
    doReturn( repositoryFileDto ).when( fileService.defaultUnifiedRepositoryWebService ).getFile( anyString() );
    doReturn( true ).when( repositoryFileDto ).isHidden();

    doReturn( stringKeyStringValueDtos ).when( fileService.defaultUnifiedRepositoryWebService )
      .getFileMetadata( anyString() );

    // Test 1
    try {
      List<StringKeyStringValueDto> list = fileService.doGetMetadata( pathId );
      assertEquals( 4, list.size() );
      Boolean hasIsHidden = false;
      Boolean hasScheduable = false;
      for ( StringKeyStringValueDto item : list ) {
        if ( item.getKey().equals( "_PERM_HIDDEN" ) ) {
          hasIsHidden = true;
        }
        if ( item.getKey().equals( RepositoryFile.SCHEDULABLE_KEY ) ) {
          hasScheduable = true;
        }
      }
      assertTrue( hasIsHidden );
      assertTrue( hasScheduable );
    } catch ( FileNotFoundException e ) {
      fail();
    }

    stringKeyStringValueDtos = new ArrayList<StringKeyStringValueDto>();
    stringKeyStringValueDtos.add( stringKeyStringValueDto1 );
    stringKeyStringValueDtos.add( stringKeyStringValueDto2 );

    StringKeyStringValueDto stringKeyStringValueDto3 = mock( StringKeyStringValueDto.class );
    doReturn( RepositoryFile.SCHEDULABLE_KEY ).when( stringKeyStringValueDto3 ).getKey();
    doReturn( "value3" ).when( stringKeyStringValueDto3 ).getValue();

    stringKeyStringValueDtos.add( stringKeyStringValueDto3 );

    doReturn( stringKeyStringValueDtos ).when( fileService.defaultUnifiedRepositoryWebService )
      .getFileMetadata( anyString() );

    // Test 2
    try {
      List<StringKeyStringValueDto> list = fileService.doGetMetadata( pathId );
      assertEquals( 4, list.size() );
      Boolean hasIsHidden = false;
      Boolean hasScheduable = false;
      for ( StringKeyStringValueDto item : list ) {
        if ( item.getKey().equals( "_PERM_HIDDEN" ) ) {
          hasIsHidden = true;
        }
        if ( item.getKey().equals( RepositoryFile.SCHEDULABLE_KEY ) ) {
          hasScheduable = true;
        }
      }
      assertTrue( hasIsHidden );
      assertTrue( hasScheduable );
    } catch ( FileNotFoundException e ) {
      fail();
    }

    doReturn( null ).when( fileService.defaultUnifiedRepositoryWebService ).getFileMetadata( anyString() );

    // Test 3
    try {
      List<StringKeyStringValueDto> list = fileService.doGetMetadata( null );
      assertEquals( null, list );
    } catch ( FileNotFoundException e ) {
      fail();
    }

    verify( fileService, times( 2 ) ).idToPath( pathId );
    verify( fileService.defaultUnifiedRepositoryWebService, times( 3 ) ).getFile( anyString() );
    verify( fileService.defaultUnifiedRepositoryWebService, times( 3 ) ).getFileMetadata( anyString() );
  }