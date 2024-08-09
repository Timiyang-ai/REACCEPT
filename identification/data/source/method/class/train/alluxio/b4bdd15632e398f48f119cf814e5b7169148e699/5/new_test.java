  @Test
  public void chown() throws Exception {
    long uid = AlluxioFuseUtils.getUid(System.getProperty("user.name"));
    long gid = AlluxioFuseUtils.getGid(System.getProperty("user.name"));
    mFuseFs.chown("/foo/bar", uid, gid);
    String userName = System.getProperty("user.name");
    String groupName = AlluxioFuseUtils.getGroupName(gid);
    AlluxioURI expectedPath = BASE_EXPECTED_URI.join("/foo/bar");
    SetAttributePOptions options =
        SetAttributePOptions.newBuilder().setGroup(groupName).setOwner(userName).build();
    verify(mFileSystem).setAttribute(expectedPath, options);
  }