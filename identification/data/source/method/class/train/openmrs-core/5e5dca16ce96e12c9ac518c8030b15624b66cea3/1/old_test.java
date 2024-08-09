	@Test
	public void getModel_shouldReturnAStartupErrorFilterModelContainingTheStartupError() {
		
		Throwable t = mock(Throwable.class);
		when(Listener.getErrorAtStartup()).thenReturn(t);
		
		StartupErrorFilter filter = new StartupErrorFilter();
		
		StartupErrorFilterModel model = filter.getModel();
		
		assertThat(model.errorAtStartup, is(t));
	}