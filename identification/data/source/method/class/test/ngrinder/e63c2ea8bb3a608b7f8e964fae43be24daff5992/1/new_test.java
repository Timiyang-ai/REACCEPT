@Test
	public void testSleep() {
		StopWatch watch = new StopWatch();
		watch.start();
		ThreadUtils.sleep(1000);
		watch.stop();
		assertThat(watch.getTime()).isGreaterThanOrEqualTo(1000);
		assertThat(watch.getTime()).isLessThan(3000);
	}