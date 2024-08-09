  @Test
  public void getTmpDir() {

    // Test single tmp dir
    String singleDir = "/tmp";
    List<String> singleDirList = Arrays.asList("/tmp");
    assertEquals(singleDir, CommonUtils.getTmpDir(singleDirList));
    // Test multiple tmp dir
    List<String> multiDirs = Arrays.asList("/tmp1", "/tmp2", "/tmp3");
    Set<String> results = new HashSet<>();
    for (int i = 0; i < 100 || results.size() != multiDirs.size(); i++) {
      results.add(CommonUtils.getTmpDir(multiDirs));
    }
    assertEquals(new HashSet<>(multiDirs), results);
  }