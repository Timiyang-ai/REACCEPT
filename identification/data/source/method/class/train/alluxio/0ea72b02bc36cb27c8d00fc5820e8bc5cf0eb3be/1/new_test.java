  @Test
  public void getPermission() throws Exception {
    try (LockedInodePath path =
             sTree.lockInodePath(new AlluxioURI(TEST_WEIRD_FILE_URI), LockPattern.READ)) {
      // user is admin
      AuthenticatedClientUser.set(TEST_USER_ADMIN.getUser());
      Mode.Bits perm = mPermissionChecker.getPermission(path);
      Assert.assertEquals(Mode.Bits.ALL, perm);

      // user is owner
      AuthenticatedClientUser.set(TEST_USER_1.getUser());
      perm = mPermissionChecker.getPermission(path);
      Assert.assertEquals(TEST_WEIRD_MODE.getOwnerBits(), perm);

      // user is not owner but in group
      AuthenticatedClientUser.set(TEST_USER_3.getUser());
      perm = mPermissionChecker.getPermission(path);
      Assert.assertEquals(TEST_WEIRD_MODE.getGroupBits(), perm);

      // user is other
      AuthenticatedClientUser.set(TEST_USER_2.getUser());
      perm = mPermissionChecker.getPermission(path);
      Assert.assertEquals(TEST_WEIRD_MODE.getOtherBits(), perm);
    }
  }