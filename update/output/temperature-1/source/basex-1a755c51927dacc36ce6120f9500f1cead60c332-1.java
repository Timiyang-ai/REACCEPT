@Test
public void installJar() throws Exception {
  // install package
  execute(new RepoInstall(REPO + "testJar.xar", null));

  // ensure package was properly installed
  final String dir = normalize("jarPkg-1.0.0");
  assertTrue("Directory check failed - not found.", isDir(dir));
  assertTrue("expath-pkg.xml not found.", isFile(dir + "/expath-pkg.xml"));
  assertTrue("basex.xml not found.", isFile(dir + "/basex.xml"));
  assertTrue("Directory 'jar' not found.", isDir(dir + "/jar"));
  assertTrue("test.jar not found.", isFile(dir + "/jar/test.jar"));
  assertTrue("wrapper.xq not found.", isFile(dir + "/jar/wrapper.xq"));

  // use package
  try (QueryProcessor qp = new QueryProcessor("import module namespace j='jar'; j:print('test')", context)) {
    String result = qp.value().serialize().toString();
    assertEquals("Test output does not match.", "test", result);
  }

  // delete package
  boolean dirDeleted = new IOFile(REPO, dir).delete();
  assertTrue("Repo directory could not be deleted.", dirDeleted);
  boolean dirExists = new IOFile(REPO, dir).exists();
  assertFalse("Repo directory still exists after deletion.", dirExists);
}