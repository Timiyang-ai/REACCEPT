	@Test
	public void skipFilter_shouldReturnTrueIfNoErrorHasOccuredOnStartup() {
		
		when(Listener.errorOccurredAtStartup()).thenReturn(false);
		
		StartupErrorFilter filter = new StartupErrorFilter();
		
		assertTrue("should be true on start without error", filter.skipFilter(new MockHttpServletRequest()));
	}