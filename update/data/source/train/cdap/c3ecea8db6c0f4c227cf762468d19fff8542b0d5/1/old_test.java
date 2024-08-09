@Test
  public void testClear() throws MetaDataException {
    testOneAddGet(false, "a", "p", "a", "x", "a", "1", null, null);
    testOneAddGet(false, "a", "q", "b", "y", "a", "2", null, null);
    testOneAddGet(false, "a", null, "c", "z", "a", "1", null, null);
    testOneAddGet(false, "b", "q", "c", "z", "a", "1", null, null);

    // clear account a, app p
    mds.clear("a", "p");
    Assert.assertNull(mds.get("a", "p", "a", "x"));
    Assert.assertNotNull(mds.get("a", "q", "b", "y"));
    Assert.assertNotNull(mds.get("a", null, "c", "z"));

    // clear all for account a
    mds.clear("a", null);
    Assert.assertNull(mds.get("a", "q", "b", "y"));
    Assert.assertNull(mds.get("a", null, "c", "z"));

    // make sure account b is still there
    Assert.assertNotNull(mds.get("b", "q", "c", "z"));
  }