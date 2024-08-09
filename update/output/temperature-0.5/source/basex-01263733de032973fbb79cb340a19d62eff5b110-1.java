@Test
public void updatedDelete() {
  // attempt to delete a package which is not installed
  try {
    new RepoManager(context).delete("xyz");
    fail("Not installed package not detected.");
  } catch(final QueryException ex) {
    // Assuming there's a specific way to check the type of exception, adapt as necessary
    assertTrue("Expected exception for not found package.", ex.getMessage().contains("not found"));
  }

  // install a package without dependencies (pkg3)
  execute(new RepoInstall(REPO + "pkg3.xar", null));

  // check if pkg3 is registered in the repo
  assertTrue("pkg3 should be registered in the repo.", context.repo.pkgDict().containsKey("pkg3"));

  // check if pkg3 was correctly unzipped
  final String pkg3Dir = normalize("pkg3");
  assertTrue("pkg3 directory should exist.", isDir(pkg3Dir));
  assertTrue("expath-pkg.xml should exist in pkg3 directory.", isFile(pkg3Dir + "/expath-pkg.xml"));

  // install another package (pkg4) with a dependency to pkg3
  execute(new RepoInstall(REPO + "pkg4.xar", null));

  // check if pkg4 is registered in the repo
  assertTrue("pkg4 should be registered in the repo.", context.repo.pkgDict().containsKey("pkg4"));

  // try to delete pkg3
  try {
    new RepoManager(context).delete("pkg3");
    fail("Package involved in a dependency was allowed to be deleted.");
  } catch(final QueryException ex) {
    // Assuming there's a specific way to check the type of exception, adapt as necessary
    assertTrue("Expected exception for deleting a package involved in a dependency.", ex.getMessage().contains("dependency"));
  }

  // try to delete pkg4 (use package name)
  execute(new RepoDelete("pkg4", null));

  // check if pkg4 is unregistered from the repo
  assertFalse("pkg4 should be unregistered from the repo.", context.repo.pkgDict().containsKey("pkg4"));

  // try to delete pkg3 (use package dir)
  execute(new RepoDelete("pkg3", null));

  // check if pkg3 is unregistered from the repo
  assertFalse("pkg3 should be unregistered from the repo.", context.repo.pkgDict().containsKey("pkg3"));
}