	@Test
	public void runInNewDaemonThread_shouldThrowErrorIfCalledFromANonDaemonThread() {
		try {
			Daemon.runInNewDaemonThread(() -> {
				// do nothing
			});
			Assert.assertTrue("Should not hit this line, since the previous needed to throw an exception", false);
		}
		catch (APIAuthenticationException ex) {
			Assert.assertEquals("Only daemon threads can spawn new daemon threads", ex.getMessage());
		}
	}