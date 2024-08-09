  @Test
  public void unlocalizeFileName() {
    assert LocalizationUtils.unlocalizeFileName("SomeFile.properties").equals("SomeFile.properties");

    assert LocalizationUtils.unlocalizeFileName("SomeFile_pt.properties").equals("SomeFile.properties");

    assert LocalizationUtils.unlocalizeFileName("SomeFile_yy.properties").equals("SomeFile_yy.properties");

    assert LocalizationUtils.unlocalizeFileName("SomeFile_pt_PT.properties").equals("SomeFile.properties");

    assert LocalizationUtils.unlocalizeFileName("SomeFile_yy_PT.properties").equals("SomeFile_yy_PT.properties");

    assert LocalizationUtils.unlocalizeFileName("SomeFile").equals("SomeFile");

    assert LocalizationUtils.unlocalizeFileName("SomeFile_pt").equals("SomeFile");

    assert LocalizationUtils.unlocalizeFileName("SomeFile_yy").equals("SomeFile_yy");

    assert LocalizationUtils.unlocalizeFileName("SomeFile_pt_PT").equals("SomeFile");

    assert LocalizationUtils.unlocalizeFileName("SomeFile_yy_PT").equals("SomeFile_yy_PT");
  }