@Test
	public void executeScheduledTask_shouldNotBeCalledFromOtherMethodsOtherThanTimerSchedulerTask() throws Throwable {
		try {
			Daemon.executeScheduledTask(new HelloWorldTask());
			Assert.fail("Should not be here, an exception should have been thrown in the line above");
		}
		catch (APIException e) {
			Assert.assertTrue(e.getMessage().startsWith("This method can only be called from the TimerSchedulerTask class"));
		}
	}