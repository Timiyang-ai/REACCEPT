@Test
  public void testLoadMetadata() throws Exception {
    FileUtils.createDir(Paths.get(mUnderFS).resolve("a").toString());
    mFileSystemMaster.loadMetadata(new AlluxioURI("alluxio:/a"),
        LoadMetadataOptions.defaults().setCreateAncestors(true));
    mFileSystemMaster.loadMetadata(new AlluxioURI("alluxio:/a"),
        LoadMetadataOptions.defaults().setCreateAncestors(true));

    // TODO(peis): Avoid this hack by adding an option in getFileInfo to skip loading metadata.
    try {
      mFileSystemMaster.createDirectory(new AlluxioURI("alluxio:/a"),
          CreateDirectoryOptions.defaults());
      fail("createDirectory was expected to fail with FileAlreadyExistsException");
    } catch (FileAlreadyExistsException e) {
      assertEquals(
          ExceptionMessage.FILE_ALREADY_EXISTS.getMessage(new AlluxioURI("alluxio:/a")),
          e.getMessage());
    }

    FileUtils.createFile(Paths.get(mUnderFS).resolve("a/f1").toString());
    FileUtils.createFile(Paths.get(mUnderFS).resolve("a/f2").toString());

    mFileSystemMaster.loadMetadata(new AlluxioURI("alluxio:/a/f1"),
        LoadMetadataOptions.defaults().setCreateAncestors(true));

    // This should not throw file exists exception those a/f1 is loaded.
    mFileSystemMaster.loadMetadata(new AlluxioURI("alluxio:/a"),
        LoadMetadataOptions.defaults().setCreateAncestors(true).setLoadDescendantType(
            DescendantType.ONE));

    // TODO(peis): Avoid this hack by adding an option in getFileInfo to skip loading metadata.
    try {
      mFileSystemMaster.createFile(new AlluxioURI("alluxio:/a/f2"), CreateFileOptions.defaults());
      fail("createDirectory was expected to fail with FileAlreadyExistsException");
    } catch (FileAlreadyExistsException e) {
      assertEquals(
          ExceptionMessage.FILE_ALREADY_EXISTS.getMessage(new AlluxioURI("alluxio:/a/f2")),
          e.getMessage());
    }

    mFileSystemMaster.loadMetadata(new AlluxioURI("alluxio:/a"),
        LoadMetadataOptions.defaults().setCreateAncestors(true)
            .setLoadDescendantType(DescendantType.ONE));
  }