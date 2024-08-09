  @Test
  public void doSetMetadata() {
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

    doReturn( true ).when( fileService.policy ).isAllowed( RepositoryReadAction.NAME );
    doReturn( true ).when( fileService.policy ).isAllowed( RepositoryCreateAction.NAME );
    doReturn( true ).when( fileService.policy ).isAllowed( AdministerSecurityAction.NAME );

    RepositoryFileDto file = mock( RepositoryFileDto.class );
    doReturn( false ).when( file ).isFolder();
    doReturn( true ).when( file ).isHidden();

    doReturn( file ).when( fileService.defaultUnifiedRepositoryWebService ).getFile( anyString() );

    List<RepositoryFileAclAceDto> repositoryFileAclAceDtos = new ArrayList<RepositoryFileAclAceDto>();

    RepositoryFileAclDto repositoryFileAclDto = mock( RepositoryFileAclDto.class );
    doReturn( "sessionName" ).when( repositoryFileAclDto ).getOwner();
    doReturn( true ).when( repositoryFileAclDto ).isEntriesInheriting();
    doReturn( repositoryFileAclAceDtos ).when( repositoryFileAclDto ).getAces();

    doReturn( repositoryFileAclDto ).when( fileService.defaultUnifiedRepositoryWebService ).getAcl( anyString() );

    IPentahoSession pentahoSession = mock( IPentahoSession.class );
    doReturn( pentahoSession ).when( fileService ).getSession();
    doReturn( "sessionName" ).when( pentahoSession ).getName();

    RepositoryFileAclAceDto repositoryFileAclAceDto = mock( RepositoryFileAclAceDto.class );
    List<Integer> permissions = new ArrayList<Integer>();
    permissions.add( RepositoryFilePermission.ACL_MANAGEMENT.ordinal() );
    doReturn( permissions ).when( repositoryFileAclAceDto ).getPermissions();
    doReturn( "sessionName" ).when( repositoryFileAclAceDto ).getRecipient();

    repositoryFileAclAceDtos.add( repositoryFileAclAceDto );

    doReturn( repositoryFileAclAceDtos ).when( fileService.defaultUnifiedRepositoryWebService )
      .getEffectiveAces( anyString() );

    Map<String, Serializable> metadata = new HashMap<String, Serializable>();
    doReturn( metadata ).when( fileService.repository ).getFileMetadata( anyString() );

    RepositoryFile sourceFile = mock( RepositoryFile.class );
    doReturn( sourceFile ).when( fileService.repository ).getFileById( anyString() );

    RepositoryFileDto destFileDto = mock( RepositoryFileDto.class );
    doReturn( destFileDto ).when( fileService ).toFileDto( sourceFile, null, false );

    RepositoryFile destFile = mock( RepositoryFile.class );
    doReturn( destFile ).when( fileService ).toFile( destFileDto );

    RepositoryFileAcl acl = mock( RepositoryFileAcl.class );
    doReturn( acl ).when( fileService.repository ).getAcl( acl );

    // Test 1 - canManage should be true at start
    try {
      fileService.doSetMetadata( pathId, stringKeyStringValueDtos );
    } catch ( GeneralSecurityException e ) {
      fail();
    }

    // Test 2 - canManage should be false at start
    doReturn( false ).when( fileService.policy ).isAllowed( RepositoryReadAction.NAME );
    doReturn( false ).when( fileService.policy ).isAllowed( RepositoryCreateAction.NAME );
    doReturn( false ).when( fileService.policy ).isAllowed( AdministerSecurityAction.NAME );
    doReturn( "sessionName1" ).when( repositoryFileAclDto ).getOwner();

    try {
      fileService.doSetMetadata( pathId, stringKeyStringValueDtos );
    } catch ( GeneralSecurityException e ) {
      fail();
    }

    // Test 3 - canManage should be false at start
    doReturn( true ).when( fileService.policy ).isAllowed( RepositoryReadAction.NAME );
    doReturn( false ).when( fileService.policy ).isAllowed( RepositoryCreateAction.NAME );
    doReturn( false ).when( fileService.policy ).isAllowed( AdministerSecurityAction.NAME );
    doReturn( "sessionName1" ).when( repositoryFileAclDto ).getOwner();

    try {
      fileService.doSetMetadata( pathId, stringKeyStringValueDtos );
    } catch ( GeneralSecurityException e ) {
      fail();
    }

    // Test 4 - canManage should be false at start
    doReturn( false ).when( fileService.policy ).isAllowed( RepositoryReadAction.NAME );
    doReturn( true ).when( fileService.policy ).isAllowed( RepositoryCreateAction.NAME );
    doReturn( false ).when( fileService.policy ).isAllowed( AdministerSecurityAction.NAME );
    doReturn( "sessionName1" ).when( repositoryFileAclDto ).getOwner();

    try {
      fileService.doSetMetadata( pathId, stringKeyStringValueDtos );
    } catch ( GeneralSecurityException e ) {
      fail();
    }

    // Test 5 - canManage should be false at start
    doReturn( false ).when( fileService.policy ).isAllowed( RepositoryReadAction.NAME );
    doReturn( false ).when( fileService.policy ).isAllowed( RepositoryCreateAction.NAME );
    doReturn( true ).when( fileService.policy ).isAllowed( AdministerSecurityAction.NAME );
    doReturn( "sessionName1" ).when( repositoryFileAclDto ).getOwner();

    try {
      fileService.doSetMetadata( pathId, stringKeyStringValueDtos );
    } catch ( GeneralSecurityException e ) {
      fail();
    }

    // Test 6 - canManage should be false at start
    doReturn( true ).when( fileService.policy ).isAllowed( RepositoryReadAction.NAME );
    doReturn( true ).when( fileService.policy ).isAllowed( RepositoryCreateAction.NAME );
    doReturn( false ).when( fileService.policy ).isAllowed( AdministerSecurityAction.NAME );
    doReturn( "sessionName1" ).when( repositoryFileAclDto ).getOwner();

    try {
      fileService.doSetMetadata( pathId, stringKeyStringValueDtos );
    } catch ( GeneralSecurityException e ) {
      fail();
    }

    // Test 7 - canManage should be false at start
    doReturn( false ).when( fileService.policy ).isAllowed( RepositoryReadAction.NAME );
    doReturn( true ).when( fileService.policy ).isAllowed( RepositoryCreateAction.NAME );
    doReturn( true ).when( fileService.policy ).isAllowed( AdministerSecurityAction.NAME );
    doReturn( "sessionName1" ).when( repositoryFileAclDto ).getOwner();

    try {
      fileService.doSetMetadata( pathId, stringKeyStringValueDtos );
    } catch ( GeneralSecurityException e ) {
      fail();
    }

    // Test 8 - canManage should be false at start
    doReturn( true ).when( file ).isFolder();
    doReturn( true ).when( file ).isHidden();

    try {
      fileService.doSetMetadata( pathId, stringKeyStringValueDtos );
    } catch ( GeneralSecurityException e ) {
      fail();
    }

    // Test 9
    StringKeyStringValueDto stringKeyStringValueDto3 = mock( StringKeyStringValueDto.class );
    doReturn( "_PERM_HIDDEN" ).when( stringKeyStringValueDto3 ).getKey();
    doReturn( "true" ).when( stringKeyStringValueDto3 ).getValue();
    stringKeyStringValueDtos.add( stringKeyStringValueDto3 );

    try {
      fileService.doSetMetadata( pathId, stringKeyStringValueDtos );
    } catch ( GeneralSecurityException e ) {
      fail();
    }

    verify( fileService.defaultUnifiedRepositoryWebService, times( 9 ) ).getFile( anyString() );
    verify( fileService.defaultUnifiedRepositoryWebService, times( 9 ) ).getAcl( anyString() );
    verify( repositoryFileAclDto, times( 9 ) ).getOwner();
    verify( fileService.policy, times( 11 ) ).isAllowed( anyString() );
    verify( fileService.repository, times( 9 ) ).getFileMetadata( anyString() );
    verify( fileService.repository, times( 7 ) ).setFileMetadata( anyString(), any( Map.class ) );
    verify( file, times( 8 ) ).setHidden( anyBoolean() );
    verify( fileService.repository, times( 8 ) ).getFileById( anyString() );
    verify( fileService, times( 8 ) ).toFileDto( any( RepositoryFile.class ), anySet(), anyBoolean() );
    verify( fileService, times( 8 ) ).toFile( any( RepositoryFileDto.class ) );
    verify( destFileDto, times( 8 ) ).setHidden( anyBoolean() );
    verify( fileService.repository, times( 8 ) ).getAcl( anyString() );
    verify( fileService.repository, times( 7 ) )
      .updateFile( any( RepositoryFile.class ), any( IRepositoryFileData.class ),
        anyString() );
    verify( fileService.repository, times( 7 ) ).updateAcl( any( RepositoryFileAcl.class ) );
    verify( fileService.repository ).updateFolder( any( RepositoryFile.class ), anyString() );
  }