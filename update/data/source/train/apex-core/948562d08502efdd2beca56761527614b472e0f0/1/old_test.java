@Test
  public void testGet()
  {
    System.out.println("get");

    CircularBuffer<Integer> instance = new CircularBuffer<Integer>(0);
    try {
      instance.get();
      fail("exception should be raised for getting from buffer which does not have data");
    }
    catch (Exception bue) {
      assert (bue instanceof BufferUnderflowException);
    }

    instance = new CircularBuffer<Integer>(10);
    try {
      instance.get();
      fail("exception should be raised for getting from buffer which does not have data");
    }
    catch (Exception bue) {
      assert (bue instanceof BufferUnderflowException);
    }

    for (int i = 0; i < 10; i++) {
      instance.add(i);
    }

    Integer i = instance.get();
    Integer j = instance.get();
    assert(i == 0 && j == 1);

    instance.add(10);

    assert(instance.size() == 9);
    assert(instance.get() == 2);
  }