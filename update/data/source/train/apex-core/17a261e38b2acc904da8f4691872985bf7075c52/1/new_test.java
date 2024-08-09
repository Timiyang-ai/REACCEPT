@Test
  public void testGet()
  {
    Thread.currentThread().setName("TestGet");

    CircularBuffer<Integer> instance = new CircularBuffer<Integer>(0);
    try {
      instance.remove();
      Assert.fail("exception should be raised for getting from buffer which does not have data");
    }
    catch (Exception bue) {
      assert (bue instanceof BufferUnderflowException);
    }

    instance = new CircularBuffer<Integer>(10);
    try {
      instance.remove();
      Assert.fail("exception should be raised for getting from buffer which does not have data");
    }
    catch (Exception bue) {
      assert (bue instanceof BufferUnderflowException);
    }

    for (int i = 0; i < 10; i++) {
      instance.add(i);
    }

    Integer i = instance.remove();
    Integer j = instance.remove();
    assert (i == 0 && j == 1);

    instance.add(10);

    assert (instance.size() == 9);
    assert (instance.remove() == 2);
  }