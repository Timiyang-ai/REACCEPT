@Test
  public void testGet_AllocFailure() throws Exception {
    TestPool pool = new TestPool(4, 5);
    pool.get(4);
    try {
      pool.get(4);
      Assert.fail();
    } catch (PoolSizeViolationException e) {
      // expected exception
    }
  }