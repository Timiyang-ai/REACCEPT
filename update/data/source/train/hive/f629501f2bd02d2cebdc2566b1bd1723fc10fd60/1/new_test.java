@Test(timeout = 10000)
  public void testSetCapacity() throws InterruptedException {
    TaskExecutorServiceForTest taskExecutorService =
        new TaskExecutorServiceForTest(2, 3, ShortestJobFirstComparator.class.getName(), true, mockMetrics);

    // Fourth is lower priority as a result of canFinish being set to false.
    MockRequest r1 = createMockRequest(1, 1, 1, 100, 200, true, 20000L, true);
    MockRequest r2 = createMockRequest(2, 1, 2, 100, 200, true, 20000L, true);
    MockRequest r3 = createMockRequest(3, 1, 3, 100, 200, true, 20000L, true);
    MockRequest r4 = createMockRequest(4, 1, 4, 100, 200, true, 20000L, false);
    MockRequest r5 = createMockRequest(5, 1, 5, 100, 200, true, 20000L, false);
    MockRequest r6 = createMockRequest(6, 1, 6, 100, 200, true, 20000L, false);
    MockRequest r7 = createMockRequest(7, 1, 7, 100, 200, true, 20000L, false);
    MockRequest r8 = createMockRequest(8, 1, 8, 100, 200, true, 20000L, false);
    MockRequest r9 = createMockRequest(9, 1, 9, 100, 200, true, 20000L, false);

    taskExecutorService.init(new Configuration());
    taskExecutorService.start();
    try {
      Scheduler.SubmissionState submissionState;
      TaskExecutorServiceForTest.InternalCompletionListenerForTest icl;

      // Schedule the first 4 tasks (2 to execute, 2 to the queue)
      submissionState = taskExecutorService.schedule(r1);
      assertEquals(Scheduler.SubmissionState.ACCEPTED, submissionState);

      submissionState = taskExecutorService.schedule(r2);
      assertEquals(Scheduler.SubmissionState.ACCEPTED, submissionState);

      submissionState = taskExecutorService.schedule(r3);
      assertEquals(Scheduler.SubmissionState.ACCEPTED, submissionState);

      submissionState = taskExecutorService.schedule(r4);
      assertEquals(Scheduler.SubmissionState.ACCEPTED, submissionState);
      // TaskExecutorService: Executing: r1, r2 + Queued: r3, r4

      awaitStartAndSchedulerRun(r1, taskExecutorService);
      awaitStartAndSchedulerRun(r2, taskExecutorService);

      // Check if the queue and the executing tasks are as expected
      assertEquals(2, taskExecutorService.waitQueue.size());
      assertEquals(0, taskExecutorService.numSlotsAvailable.get());

      // Change the capacity
      taskExecutorService.setCapacity(1, 1);

      // Check that the actual queue size is not changed, but the available executor size is changed
      assertEquals(1, taskExecutorService.waitQueue.waitQueueSize);
      assertEquals(1, taskExecutorService.maxParallelExecutors);
      assertEquals(2, taskExecutorService.waitQueue.size());
      assertEquals(-1, taskExecutorService.numSlotsAvailable.get());

      // Try to schedule one more task, it should be rejected now
      submissionState = taskExecutorService.schedule(r5);
      assertEquals(Scheduler.SubmissionState.REJECTED, submissionState);
      // TaskExecutorService: Executing: r1, r2 + Queued: r3, r4

      // Complete r1
      r1.awaitStart();
      r1.complete();
      r1.awaitEnd();
      icl = taskExecutorService.getInternalCompletionListenerForTest(r1.getRequestId());
      icl.awaitCompletion();
      // TaskExecutorService: Executing: r2 + Queued: r3, r4

      // Check if it is really finished
      assertEquals(2, taskExecutorService.waitQueue.size());
      assertEquals(0, taskExecutorService.numSlotsAvailable.get());

      // Complete r2
      r2.awaitStart();
      r2.complete();
      r2.awaitEnd();
      icl = taskExecutorService.getInternalCompletionListenerForTest(r2.getRequestId());
      icl.awaitCompletion();
      // TaskExecutorService: Executing: r3 + Queued: r4

      // Wait for a scheduling attempt, after that wait queue should be reduced
      awaitStartAndSchedulerRun(r3, taskExecutorService);
      assertEquals(1, taskExecutorService.waitQueue.size());
      assertEquals(0, taskExecutorService.numSlotsAvailable.get());

      // Try to schedule one more task, it still should be rejected
      submissionState = taskExecutorService.schedule(r6);
      assertEquals(Scheduler.SubmissionState.REJECTED, submissionState);
      // TaskExecutorService: Executing: r3 + Queued: r4

      // Complete r3
      r3.complete();
      r3.awaitEnd();
      icl = taskExecutorService.getInternalCompletionListenerForTest(r3.getRequestId());
      icl.awaitCompletion();
      // TaskExecutorService: Executing: r4 + Queued: -

      // Try to schedule one more task, it still should accepted finally
      submissionState = taskExecutorService.schedule(r7);
      // TaskExecutorService: Executing: r4 + Queued: r7
      assertEquals(Scheduler.SubmissionState.ACCEPTED, submissionState);

      // Change back the capacity
      taskExecutorService.setCapacity(2, 3);
      assertEquals(3, taskExecutorService.waitQueue.waitQueueSize);
      assertEquals(2, taskExecutorService.maxParallelExecutors);
      // TaskExecutorService Executing: r4, r7 + Queued: -

      // Wait for a scheduling attempt, the new task should be started
      awaitStartAndSchedulerRun(r7, taskExecutorService);
      assertEquals(0, taskExecutorService.waitQueue.size());
      assertEquals(0, taskExecutorService.numSlotsAvailable.get());

      submissionState = taskExecutorService.schedule(r8);
      assertEquals(Scheduler.SubmissionState.ACCEPTED, submissionState);
      // TaskExecutorService: Executing: r4, r7 + Queued: r8

      submissionState = taskExecutorService.schedule(r9);
      assertEquals(Scheduler.SubmissionState.ACCEPTED, submissionState);
      // TaskExecutorService: Executing: r4, r7 + Queued: r8, r9

      assertEquals(2, taskExecutorService.waitQueue.size());
      assertEquals(0, taskExecutorService.numSlotsAvailable.get());

    } finally {
      taskExecutorService.shutDown(false);
    }
  }