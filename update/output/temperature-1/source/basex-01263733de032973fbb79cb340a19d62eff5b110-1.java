@Test
public void delete() {
  try {
    new RepoManager(context).delete("xyz");
    fail("Deletion of a non-existent package should have thrown an exception.");
  } catch(final QueryException ex) {
    assertNotNull("Exception should contain a message.", ex.getMessage());
  }

  execute(new RepoInstall(REPO + "pkg3.xar", null));
  assertTrue("Package pkg3 should be registered after installation.", context.repo.pkgDict().containsKey(PKG3ID));

  assertTrue("Package directory for pkg3 should exist.", isDir(normalize(PKG3ID)));

  execute(new RepoInstall(REPO + "pkg4.xar", null));
  assertTrue("Package pkg4 should be registered after installation.", context.repo.pkgDict().containsKey(PKG4ID));

  assertTrue("Package directory for pkg4 should exist.", isDir(normalize(PKG4ID)));

  try {
    new RepoManager(context).delete(PKG3ID);
    fail("Attempt to delete a package with dependencies should fail.");
  } catch(final QueryException ex) {
    assertNotNull("Exception should contain a message for failing to delete a package due to dependencies.", ex.getMessage());
  }

  execute(new RepoDelete(PKG4, null));
  assertFalse("Package pkg4 should be unregistered after deletion.", context.repo.pkgDict().containsKey(PKG4ID));
  assertFalse("Package directory for pkg4 should not exist after deletion.", isDir(normalize(PKG4ID)));

  execute(new RepoDelete(PKG3ID, null));
  assertFalse("Package pkg3 should be unregistered after deletion.", context.repo.pkgDict().containsKey(PKG3ID));
  assertFalse("Package directory for pkg3 should not exist after deletion.", isDir(normalize(PKG3ID)));
}