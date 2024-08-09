@Test
	public void runInNewDaemonThread_shouldThrowErrorIfCalledFromANonDaemonThread() {
		try {
			Daemon.runInNewDaemonThread(new Runnable() {
				
				@Override
				public void run() {
					// do nothing
				}
			});
			Assert.assertTrue("Should not hit this line, since the previous needed to throw an exception", false);
		}
		catch (APIAuthenticationException ex) {
			Assert.assertEquals("Can only be called from a Daemon thread", ex.getMessage());
		}
	}