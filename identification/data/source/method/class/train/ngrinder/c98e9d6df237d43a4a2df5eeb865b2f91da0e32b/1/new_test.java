@Test
	public void testStopQuetly() {
		Thread newThread = new Thread(new Runnable() {

			@Override
			public void run() {
				int i = 10;
				while (i > 0) {
					ThreadUtil.sleep(200);
					System.out.println("Running...");
				}

			}
		});
		newThread.start();
		ThreadUtil.sleep(500);
		ThreadUtil.stopQuietly(newThread, "STOPPED!");
	}