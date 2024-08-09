	@Test
	public void test_lock() {

		WebcamLock lock = new WebcamLock(webcam);
		lock.lock();

		Assertions
			.assertThat(lock.isLocked())
			.isTrue();

		lock.unlock();

		Assertions
			.assertThat(lock.isLocked())
			.isFalse();
	}