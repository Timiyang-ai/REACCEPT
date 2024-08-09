@Test
public void installJar() throws Exception {
    // install package
    execute(new RepoInstall(REPO + "testJar.xar", null));

    // ensure package was properly installed
    final String dir = normalize("jarPkg-1.0.0");
    assertTrue(isDir(dir));
    assertTrue(isFile(dir + "/expath-pkg.xml"));
    assertTrue(isFile(dir + "/basex.xml"));
    assertTrue(isDir(dir + "/jar"));
    assertTrue(isFile(dir + "/jar/test.jar"));
    assertTrue(isFile(dir + "/jar/wrapper.xq"));

    // use package
    try(QueryProcessor qp = new QueryProcessor(
        "import module namespace j='jar'; j:print('test')", context)) {
      assertEquals(qp.value().serialize().toString(), "test");
    }

    // delete package
    assertTrue("Repo directory could not be deleted.", new IOFile(REPO, dir).delete());
    assertFalse(new IOFile(REPO, dir).exists());
}