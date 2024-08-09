@Test
  public void setAttributeTest() throws Exception {
    mFileSystemMaster.createFile(NESTED_FILE_URI, sNestedFileOptions);

    mFileSystemMaster.setAttribute(NESTED_FILE_URI, SetAttributeOptions.defaults());

    Assert.assertEquals(1, mCounters.get(MasterSource.SET_ATTRIBUTE_OPS).getCount());
  }