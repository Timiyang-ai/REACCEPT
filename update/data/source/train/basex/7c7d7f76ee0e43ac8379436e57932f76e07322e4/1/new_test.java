@Test
  public void testDelete() throws BaseXException {
    // try to delete a package which is not installed
    try {
      new RepoManager(ctx.repo).delete("xyz", null);
      fail("Not installed package not detected.");
    } catch(final QueryException ex) {
      check(ex, Err.PKGNOTINST);
    }
    // install a package without dependencies (pkg3)
    new RepoInstall(REPO + "pkg3.xar", null).execute(ctx);

    // check if pkg3 is registered in the repo
    assertNotNull(ctx.repo.pkgDict().id(token("pkg3-10.0")) != 0);
    // check if pkg3 was correctly unzipped
    assertTrue(dir("pkg3"));
    assertTrue(file("pkg3/expath-pkg.xml"));
    assertTrue(dir("pkg3/pkg3"));
    assertTrue(dir("pkg3/pkg3/mod"));
    assertTrue(file("pkg3/pkg3/mod/pkg3mod1.xql"));

    // install another package (pkg4) with a dependency to pkg3
    new RepoInstall(REPO + "pkg4.xar", null).execute(ctx);
    // check if pkg4 is registered in the repo
    assertNotNull(ctx.repo.pkgDict().id(token("pkg4-2.0")) != 0);
    // check if pkg4 was correctly unzipped
    assertTrue(dir("pkg4"));
    assertTrue(file("pkg4/expath-pkg.xml"));
    assertTrue(dir("pkg4/pkg4"));
    assertTrue(dir("pkg4/pkg4/mod"));
    assertTrue(file("pkg4/pkg4/mod/pkg4mod1.xql"));

    // try to delete pkg3
    try {
      new RepoManager(ctx.repo).delete("pkg3", null);
      fail("Package involved in a dependency was deleted.");
    } catch(final QueryException ex) {
      check(ex, Err.PKGDEP);
    }
    // try to delete pkg4 (use package name)
    new RepoDelete("http://www.pkg4.com", null).execute(ctx);
    // check if pkg4 is unregistered from the repo
    assertTrue(ctx.repo.pkgDict().id(token("pkg4-2.0")) == 0);

    // check if pkg4 directory was deleted
    assertTrue(!dir("pkg4"));
    // try to delete pkg3 (use package dir)
    new RepoDelete("pkg3", null).execute(ctx);
    // check if pkg3 is unregistered from the repo
    assertTrue(ctx.repo.pkgDict().id(token("pkg3-10.0")) == 0);
    // check if pkg3 directory was deleted
    assertTrue(!dir("pkg3"));
  }