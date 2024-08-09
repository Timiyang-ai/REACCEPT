@Test
  public void testAdd()
  {
    System.out.println("add");

    CircularBuffer<Integer> instance = new CircularBuffer<Integer>(0);
    try {
      instance.add(new Integer(0));
      fail("exception should be raised for adding to buffer which does not have room");
    }
    catch (Exception bue) {
      assert (bue instanceof BufferOverflowException);
    }

    instance = new CircularBuffer<Integer>(10);
    for (int i = 0; i < 10; i++) {
      instance.add(i);
    }

    assert (instance.size() == 10);
    try {
      instance.add(new Integer(0));
      fail("exception should have been thrown");
    }
    catch (Exception e) {
      assert (e instanceof BufferOverflowException);
      instance.get();
      instance.add(new Integer(0));
    }

    assert (instance.size() == 10);
  }