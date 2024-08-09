@Test
  public void testClear() throws OperationException {
    testOneAddGet(false, "a", "p", "a", "x", "a", "1", null, null);
    testOneAddGet(false, "a", "q", "b", "y", "a", "2", null, null);
    testOneAddGet(false, "a", null, "c", "z", "a", "1", null, null);
    testOneAddGet(false, "b", "q", "c", "z", "a", "1", null, null);

    // clear account a, app p
    mds.clear(context, "a", "p");
    Assert.assertNull(mds.get(context, "a", "p", "a", "x"));
    Assert.assertNotNull(mds.get(context, "a", "q", "b", "y"));
    Assert.assertNotNull(mds.get(context, "a", null, "c", "z"));

    // clear all for account a
    mds.clear(context, "a", null);
    Assert.assertNull(mds.get(context, "a", "q", "b", "y"));
    Assert.assertNull(mds.get(context, "a", null, "c", "z"));

    // make sure account b is still there
    Assert.assertNotNull(mds.get(context, "b", "q", "c", "z"));
  }