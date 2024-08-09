@Test
  @LocalAlluxioClusterResource.Config(
      confParams = {PropertyKey.Name.SECURITY_AUTHORIZATION_PERMISSION_ENABLED, "true",
          PropertyKey.Name.SECURITY_AUTHENTICATION_TYPE, "SIMPLE",
          PropertyKey.Name.SECURITY_GROUP_MAPPING_CLASS,
          "alluxio.security.group.provider.IdentityUserGroupsMapping",
          PropertyKey.Name.SECURITY_AUTHORIZATION_PERMISSION_SUPERGROUP, "test_user_ls"})
  public void ls() throws Exception {
    String testUser = "test_user_ls";
    clearAndLogin(testUser);
    URIStatus[] files = createFiles();
    mFsShell.run("ls", "/testRoot");
    String expected = "";
    expected += getLsResultStr("/testRoot/testFileA", files[0].getCreationTimeMs(), 10,
        LsCommand.STATE_FILE_IN_ALLUXIO, testUser, testUser, files[0].getMode(),
        files[0].isFolder());
    expected +=
        getLsResultStr("/testRoot/testDir", files[1].getCreationTimeMs(), 1, LsCommand.STATE_FOLDER,
            testUser, testUser, files[1].getMode(), files[1].isFolder());
    expected += getLsResultStr("/testRoot/testFileC", files[3].getCreationTimeMs(), 30,
        LsCommand.STATE_FILE_NOT_IN_ALLUXIO, testUser, testUser, files[3].getMode(),
        files[3].isFolder());
    Assert.assertEquals(expected, mOutput.toString());
  }