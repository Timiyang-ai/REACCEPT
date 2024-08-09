@Test
public void testDelete() throws BaseXException {
    // Install a package without dependencies (pkg3)
    new RepoInstall(REPO + "pkg3.xar", null).execute(ctx);
    // Check if pkg3 is registered in the repo
    assertTrue(ctx.repo.pkgDict().id(token("pkg3-10.0")) != 0);
    // Check if pkg3 was correctly unzipped
    final File pkgDir1 = new File(REPO + "pkg3");
    assertTrue(pkgDir1.exists());
    assertTrue(pkgDir1.isDirectory());
    final File pkgDesc1 = new File(REPO + "pkg3/expath-pkg.xml");
    assertTrue(pkgDesc1.exists());
    final File modDir1 = new File(REPO + "pkg3/pkg3/mod");
    assertTrue(modDir1.exists());
    assertTrue(modDir1.isDirectory());
    final File modFile1 = new File(REPO + "pkg3/pkg3/mod/pkg3mod1.xql");
    assertTrue(modFile1.exists());
    // Install another package (pkg4) with a dependency to pkg3
    new RepoInstall(REPO + "pkg4.xar", null).execute(ctx);
    // Check if pkg4 is registered in the repo
    assertTrue(ctx.repo.pkgDict().id(token("pkg4-2.0")) != 0);
    // Check if pkg4 was correctly unzipped
    final File pkgDir2 = new File(REPO + "pkg4");
    assertTrue(pkgDir2.exists());
    assertTrue(pkgDir2.isDirectory());
    final File pkgDesc2 = new File(REPO + "pkg4/expath-pkg.xml");
    assertTrue(pkgDesc2.exists());
    final File modDir2 = new File(REPO + "pkg4/pkg4/mod");
    assertTrue(modDir2.exists());
    assertTrue(modDir2.isDirectory());
    final File modFile2 = new File(REPO + "pkg4/pkg4/mod/pkg4mod1.xql");
    assertTrue(modFile2.exists());
    // Try to delete pkg3
    try {
      new RepoManager(ctx).delete("pkg3", null);
      fail("Package involved in a dependency was deleted.");
    } catch(final QueryException ex) {
      check(ex, Err.PKGDEP);
    }
    // Try to delete pkg4 (use package name)
    new RepoDelete("http://www.pkg4.com", null).execute(ctx);
    // Check if pkg4 is unregistered from the repo
    assertTrue(ctx.repo.pkgDict().id(token("pkg4-2.0")) == 0);
    // Check if pkg4 directory was deleted
    assertFalse(pkgDir2.exists());
    // Try to delete pkg3 (use package dir)
    new RepoDelete("pkg3", null).execute(ctx);
    // Check if pkg3 is unregistered from the repo
    assertTrue(ctx.repo.pkgDict().id(token("pkg3-10.0")) == 0);
    // Check if pkg3 directory was deleted
    assertFalse(pkgDir1.exists());

    // Try to delete a non-existing package
    try {
      new RepoManager(ctx).delete("xyz", null);
      fail("Not installed package not detected.");
    } catch(final QueryException ex) {
      check(ex, Err.PKGNOTEXIST);
    }
}