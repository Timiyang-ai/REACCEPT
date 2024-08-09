@Test
  public void testClearFabric() {
    final byte[] a = { 'a' };
    final byte[] x = { 'x' };
    final byte[] q = "queue://q".getBytes();
    final byte[] s = "stream://s".getBytes();

    // write to a table, a queue, and a stream
    Assert.assertTrue(remote.execute(new Write(a, x)));
    Assert.assertTrue(remote.execute(new QueueEnqueue(q, x)));
    Assert.assertTrue(remote.execute(new QueueEnqueue(s, x)));

    // clear everything
    remote.execute(new ClearFabric(true, true, true));

    // verify that all is gone
    Assert.assertNull(remote.execute(new ReadKey(a)));
    QueueConsumer consumer = new QueueConsumer(0, 1, 1);
    QueueConfig config = new
        QueueConfig(new QueuePartitioner.RandomPartitioner(), true);
    Assert.assertTrue(
        remote.execute(new QueueDequeue(q, consumer, config)).isEmpty());
    Assert.assertTrue(
        remote.execute(new QueueDequeue(s, consumer, config)).isEmpty());

    // write back all values
    Assert.assertTrue(remote.execute(new Write(a, x)));
    Assert.assertTrue(remote.execute(new QueueEnqueue(q, x)));
    Assert.assertTrue(remote.execute(new QueueEnqueue(s, x)));

    // clear only the tables
    remote.execute(new ClearFabric(true, false, false));

    // verify that the tables are gone, but queues and streams are there
    Assert.assertNull(remote.execute(new ReadKey(a)));
    Assert.assertArrayEquals(x,
        remote.execute(new QueueDequeue(q, consumer, config)).getValue());
    Assert.assertArrayEquals(x,
        remote.execute(new QueueDequeue(s, consumer, config)).getValue());

    // write back to the table
    Assert.assertTrue(remote.execute(new Write(a, x)));

    // clear only the queues
    remote.execute(new ClearFabric(false, true, false));

    // verify that the queues are gone, but tables and streams are there
    Assert.assertArrayEquals(x, remote.execute(new ReadKey(a)));
    Assert.assertTrue(
        remote.execute(new QueueDequeue(q, consumer, config)).isEmpty());
    Assert.assertArrayEquals(x,
        remote.execute(new QueueDequeue(s, consumer, config)).getValue());

    // write back to the queue
    Assert.assertTrue(remote.execute(new QueueEnqueue(q, x)));

    // clear only the streams
    remote.execute(new ClearFabric(false, false, true));

    // verify that the streams are gone, but tables and queues are there
    Assert.assertArrayEquals(x, remote.execute(new ReadKey(a)));
    Assert.assertArrayEquals(x,
        remote.execute(new QueueDequeue(q, consumer, config)).getValue());
    Assert.assertTrue(
        remote.execute(new QueueDequeue(s, consumer, config)).isEmpty());
  }