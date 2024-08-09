  @Before
  public void init() {
    mockedDataBase = mock( Database.class );
    mockedJob = mock( Job.class );
    mockedVariableSpace = mock( VariableSpace.class );
    hasDatabasesInterface = mock( HasDatabasesInterface.class );
    mockedJobMeta = mock( JobMeta.class );
    mockedJobEntryCopy = mock( JobEntryCopy.class );
    mockedJobEntrySpecial = mock( JobEntrySpecial.class );
    mockedLogChannel = mock( LogChannel.class );

    when( mockedJob.createDataBase( any( DatabaseMeta.class ) ) ).thenReturn( mockedDataBase );
  }