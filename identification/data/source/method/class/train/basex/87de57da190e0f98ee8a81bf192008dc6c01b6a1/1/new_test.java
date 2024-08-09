@Test
  public void delete() throws BaseXException {
    // try to delete a package which is not installed
    try {
      new RepoManager(ctx).delete("xyz");
      fail("Not installed package not detected.");
    } catch(final QueryException ex) {
      check(ex, Err.PKGNOTEXIST);
    }
    // install a package without dependencies (pkg3)
    new RepoInstall(REPO + "pkg3.xar", null).execute(ctx);

    // check if pkg3 is registered in the repo
    assertTrue(ctx.repo.pkgDict().contains(token("http://www.pkg3.com-10.0")));

    // check if pkg3 was correctly unzipped
    final String pkg3Dir = normalize("http://www.pkg3.com-10.0");
    assertTrue(dir(pkg3Dir));
    assertTrue(file(pkg3Dir + "/expath-pkg.xml"));
    assertTrue(dir(pkg3Dir + "/pkg3"));
    assertTrue(dir(pkg3Dir + "/pkg3/mod"));
    assertTrue(file(pkg3Dir + "/pkg3/mod/pkg3mod1.xql"));

    // install another package (pkg4) with a dependency to pkg3
    new RepoInstall(REPO + "pkg4.xar", null).execute(ctx);
    // check if pkg4 is registered in the repo
    assertTrue(ctx.repo.pkgDict().contains(token("http://www.pkg4.com-2.0")));
    // check if pkg4 was correctly unzipped
    final String pkg4Dir = normalize("http://www.pkg4.com-2.0");
    assertTrue(dir(pkg4Dir));
    assertTrue(file(pkg4Dir + "/expath-pkg.xml"));
    assertTrue(dir(pkg4Dir + "/pkg4"));
    assertTrue(dir(pkg4Dir + "/pkg4/mod"));
    assertTrue(file(pkg4Dir + "/pkg4/mod/pkg4mod1.xql"));

    // try to delete pkg3
    try {
      new RepoManager(ctx).delete(pkg3Dir);
      fail("Package involved in a dependency was deleted.");
    } catch(final QueryException ex) {
      check(ex, Err.PKGDEP);
    }
    // try to delete pkg4 (use package name)
    new RepoDelete("http://www.pkg4.com", null).execute(ctx);
    // check if pkg4 is unregistered from the repo
    assertFalse(ctx.repo.pkgDict().contains(token("http://www.pkg4.com-2.0")));

    // check if pkg4 directory was deleted
    assertTrue(!dir(pkg4Dir));
    // try to delete pkg3 (use package dir)
    new RepoDelete(pkg3Dir, null).execute(ctx);
    // check if pkg3 is unregistered from the repo
    assertFalse(ctx.repo.pkgDict().contains(token("http://www.pkg3.com-10.0")));
    // check if pkg3 directory was deleted
    assertTrue(!dir(pkg3Dir));
  }