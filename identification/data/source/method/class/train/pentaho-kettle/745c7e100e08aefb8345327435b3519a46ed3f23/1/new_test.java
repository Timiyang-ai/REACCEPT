  @Test( expected = NullPointerException.class )
  public void checkUserInfo_Null() {
    RepositoryCommonValidations.checkUserInfo( null );
  }