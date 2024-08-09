  @Test
  public void getFormattedFileList() {
    fileResult.addFile(new File("file1.txt"), FileResultModel.FILE_TYPE_FILE);
    fileResult.addFile(new File("file2.txt"), FileResultModel.FILE_TYPE_FILE);
    assertThat(fileResult.getFormattedFileList()).isEqualTo("file1.txt, file2.txt");
  }