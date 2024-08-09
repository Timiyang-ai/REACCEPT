@Test
  public void delete() {
    // try to delete a package which is not installed
    try {
      new RepoManager(context).delete("xyz");
      fail("Not installed package not detected.");
    } catch(final QueryException ex) {
      check(null, ex, BXRE_WHICH_X);
    }
    // install a package without dependencies (pkg3)
    execute(new RepoInstall(REPO + "pkg3.xar", null));

    // check if pkg3 is registered in the repo
    assertTrue(context.repo.pkgDict().containsKey(PKG3ID));

    // check if pkg3 was correctly unzipped
    final String pkg3Dir = normalize(PKG3ID);
    assertTrue(isDir(pkg3Dir));
    assertTrue(isFile(pkg3Dir + "/expath-pkg.xml"));
    assertTrue(isDir(pkg3Dir + "/pkg3"));
    assertTrue(isDir(pkg3Dir + "/pkg3/mod"));
    assertTrue(isFile(pkg3Dir + "/pkg3/mod/pkg3mod1.xql"));

    // install another package (pkg4) with a dependency to pkg3
    execute(new RepoInstall(REPO + "pkg4.xar", null));
    // check if pkg4 is registered in the repo
    assertTrue(context.repo.pkgDict().containsKey(PKG4ID));
    // check if pkg4 was correctly unzipped
    final String pkg4Dir = normalize(PKG4ID);
    assertTrue(isDir(pkg4Dir));
    assertTrue(isFile(pkg4Dir + "/expath-pkg.xml"));
    assertTrue(isDir(pkg4Dir + "/pkg4"));
    assertTrue(isDir(pkg4Dir + "/pkg4/mod"));
    assertTrue(isFile(pkg4Dir + "/pkg4/mod/pkg4mod1.xql"));

    // try to delete pkg3
    try {
      new RepoManager(context).delete(PKG3ID);
      fail("Package involved in a dependency was deleted.");
    } catch(final QueryException ex) {
      check(null, ex, BXRE_DEP_X_X);
    }
    // try to delete pkg4 (use package name)
    execute(new RepoDelete(PKG4, null));
    // check if pkg4 is unregistered from the repo
    assertFalse(context.repo.pkgDict().containsKey(PKG4ID));

    // check if pkg4 directory was deleted
    assertFalse(isDir(pkg4Dir));
    // try to delete pkg3 (use package dir)
    execute(new RepoDelete(PKG3ID, null));
    // check if pkg3 is unregistered from the repo
    assertFalse(context.repo.pkgDict().containsKey(PKG3ID));
    // check if pkg3 directory was deleted
    assertFalse(isDir(pkg3Dir));
  }