@Test
  public void applyUMaskTest() {
    FileSystemPermission umaskPermission = new FileSystemPermission((short) 0022);
    PermissionStatus permissionStatus =
        new PermissionStatus("user1", "group1", FileSystemPermission.getDefault());
    permissionStatus.applyUMask(umaskPermission);

    Assert.assertEquals(FileSystemAction.ALL, permissionStatus.getPermission().getUserAction());
    Assert.assertEquals(FileSystemAction.READ_EXECUTE,
        permissionStatus.getPermission().getGroupAction());
    Assert.assertEquals(FileSystemAction.READ_EXECUTE,
        permissionStatus.getPermission().getOtherAction());
    verifyPermissionStatus("user1", "group1", (short) 0755, permissionStatus);
  }