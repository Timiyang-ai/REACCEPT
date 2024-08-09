	@Test
	public void wrapException() {
		ScheduledThreadPoolExecutor executor = ThreadPoolBuilder.scheduledPool().build();
		ExceptionTask task = new ExceptionTask();
		executor.scheduleAtFixedRate(task, 0, 100, TimeUnit.MILLISECONDS);

		ThreadUtil.sleep(500);

		// 线程第一次跑就被中断
		assertThat(task.counter.get()).isEqualTo(1);
		ThreadPoolUtil.gracefulShutdown(executor, 1000);

		////////
		executor = ThreadPoolBuilder.scheduledPool().build();
		ExceptionTask newTask = new ExceptionTask();
		Runnable wrapTask = ThreadPoolUtil.safeRunnable(newTask);
		executor.scheduleAtFixedRate(wrapTask, 0, 100, TimeUnit.MILLISECONDS);

		ThreadUtil.sleep(500);
		assertThat(newTask.counter.get()).isGreaterThan(2);
		System.out.println("-------actual run:" + task.counter.get());
		ThreadPoolUtil.gracefulShutdown(executor, 1000);

	}