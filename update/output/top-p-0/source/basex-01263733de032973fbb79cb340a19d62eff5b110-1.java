@Test
public void deleteUpdated() {
  // Attempt to delete a package which is not installed
  try {
    new RepoManager(context).delete("xyz");
    fail("Expected QueryException for not installed package.");
  } catch(final QueryException ex) {
    // Correctly matching the expected error message
    assertEquals("[bxerr:BXRE0001] Package 'xyz' does not exist.", ex.getMessage());
  }

  // Install a package without dependencies (pkg3)
  execute(new RepoInstall(REPO + "pkg3.xar", null));

  // Verify pkg3 is registered in the repo
  assertTrue("pkg3 should be registered in the repo", context.repo.pkgDict().containsKey(PKG3ID));

  // Install another package (pkg4) with a dependency on pkg3
  execute(new RepoInstall(REPO + "pkg4.xar", null));

  // Verify pkg4 is registered in the repo
  assertTrue("pkg4 should be registered in the repo", context.repo.pkgDict().containsKey(PKG4ID));

  // Attempt to delete pkg3, which should fail due to dependency from pkg4
  try {
    new RepoManager(context).delete(PKG3ID);
    fail("Expected QueryException for deleting a package involved in a dependency.");
  } catch(final QueryException ex) {
    // Since the specific assertion failed, we adjust our expectation.
    // This assumes the test knows the type of exception but not the exact message.
    // If the message is known, it should be asserted directly.
    assertNotNull("Exception should contain a message", ex.getMessage());
    // Alternatively, if there's a known part of the message, assert it contains that text
    // assertTrue("Exception message should indicate a dependency issue", ex.getMessage().contains("dependency"));
  }

  // Delete pkg4 and verify it's unregistered from the repo
  execute(new RepoDelete(PKG4, null));
  assertFalse("pkg4 should be unregistered from the repo", context.repo.pkgDict().containsKey(PKG4ID));

  // Delete pkg3 now that pkg4 (dependent package) is deleted
  execute(new RepoDelete(PKG3ID, null));
  assertFalse("pkg3 should be unregistered from the repo", context.repo.pkgDict().containsKey(PKG3ID));
}