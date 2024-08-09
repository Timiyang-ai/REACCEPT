@Test
  public void delete() throws BaseXException {
    // try to delete a package which is not installed
    try {
      new RepoManager(context).delete(token("xyz"));
      fail("Not installed package not detected.");
    } catch(final QueryException ex) {
      check(null, ex, Err.BXRE_WHICH);
    }
    // install a package without dependencies (pkg3)
    new RepoInstall(REPO + "pkg3.xar", null).execute(context);

    // check if pkg3 is registered in the repo
    assertTrue(context.repo.pkgDict().contains(token(PKG3ID)));

    // check if pkg3 was correctly unzipped
    final String pkg3Dir = normalize(PKG3ID);
    assertTrue(dir(pkg3Dir));
    assertTrue(file(pkg3Dir + "/expath-pkg.xml"));
    assertTrue(dir(pkg3Dir + "/pkg3"));
    assertTrue(dir(pkg3Dir + "/pkg3/mod"));
    assertTrue(file(pkg3Dir + "/pkg3/mod/pkg3mod1.xql"));

    // install another package (pkg4) with a dependency to pkg3
    new RepoInstall(REPO + "pkg4.xar", null).execute(context);
    // check if pkg4 is registered in the repo
    assertTrue(context.repo.pkgDict().contains(token(PKG4ID)));
    // check if pkg4 was correctly unzipped
    final String pkg4Dir = normalize(PKG4ID);
    assertTrue(dir(pkg4Dir));
    assertTrue(file(pkg4Dir + "/expath-pkg.xml"));
    assertTrue(dir(pkg4Dir + "/pkg4"));
    assertTrue(dir(pkg4Dir + "/pkg4/mod"));
    assertTrue(file(pkg4Dir + "/pkg4/mod/pkg4mod1.xql"));

    // try to delete pkg3
    try {
      new RepoManager(context).delete(token(PKG3ID));
      fail("Package involved in a dependency was deleted.");
    } catch(final QueryException ex) {
      check(null, ex, Err.BXRE_DEP);
    }
    // try to delete pkg4 (use package name)
    new RepoDelete(PKG4, null).execute(context);
    // check if pkg4 is unregistered from the repo
    assertFalse(context.repo.pkgDict().contains(token(PKG4ID)));

    // check if pkg4 directory was deleted
    assertFalse(dir(pkg4Dir));
    // try to delete pkg3 (use package dir)
    new RepoDelete(PKG3ID, null).execute(context);
    // check if pkg3 is unregistered from the repo
    assertFalse(context.repo.pkgDict().contains(token(PKG3ID)));
    // check if pkg3 directory was deleted
    assertFalse(dir(pkg3Dir));
  }