  @Test
  public void throwIfNotInThisSynchronizationContext() throws Exception {
    final AtomicBoolean taskSuccess = new AtomicBoolean(false);
    final CountDownLatch task1Running = new CountDownLatch(1);
    final CountDownLatch task1Proceed = new CountDownLatch(1);

    doAnswer(new Answer<Void>() {
        @Override
        public Void answer(InvocationOnMock invocation) {
          task1Running.countDown();
          syncContext.throwIfNotInThisSynchronizationContext();
          try {
            assertTrue(task1Proceed.await(5, TimeUnit.SECONDS));
          } catch (InterruptedException e) {
            throw new RuntimeException(e);
          }
          taskSuccess.set(true);
          return null;
        }
      }).when(task1).run();

    Thread sideThread = new Thread() {
        @Override
        public void run() {
          syncContext.execute(task1);
        }
      };
    sideThread.start();

    assertThat(task1Running.await(5, TimeUnit.SECONDS)).isTrue();

    // syncContext is draining, but the current thread is not in the context
    try {
      syncContext.throwIfNotInThisSynchronizationContext();
      fail("Should throw");
    } catch (IllegalStateException e) {
      assertThat(e.getMessage()).isEqualTo("Not called from the SynchronizationContext");
    }

    // Let task1 finish
    task1Proceed.countDown();
    sideThread.join();

    // throwIfNotInThisSynchronizationContext() didn't throw in task1
    assertThat(taskSuccess.get()).isTrue();

    // syncContext is not draining, but the current thread is not in the context
    try {
      syncContext.throwIfNotInThisSynchronizationContext();
      fail("Should throw");
    } catch (IllegalStateException e) {
      assertThat(e.getMessage()).isEqualTo("Not called from the SynchronizationContext");
    }
  }