	@Test
	public void testStartEventDispatcher() throws IllegalArgumentException, NoSuchFieldException, IllegalAccessException {
		System.out.println("startEventDispatcher");

		Field eventExecutor = GlobalScreen.class.getDeclaredField("eventExecutor");
		eventExecutor.setAccessible(true);
		assertNotNull(eventExecutor.get(GlobalScreen.class));
	}