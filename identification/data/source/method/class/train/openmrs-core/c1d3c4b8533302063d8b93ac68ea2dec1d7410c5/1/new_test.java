	@Test
	public void shouldResourceBeIncluded_shouldReturnTrueIfFileMatchesAndOpenmrsVersionMatches()
	        throws MalformedURLException {
		ModuleConditionalResource resource = new ModuleConditionalResource();
		resource.setPath("lib/mockmodule-api-1.10.jar");
		resource.setOpenmrsPlatformVersion("1.7-1.8,1.10-1.11");
		
		mockModule.getConditionalResources().add(resource);
		
		boolean result = ModuleClassLoader.shouldResourceBeIncluded(mockModule, URI.create(
		    "file://module/mockmodule/lib/mockmodule-api-1.10.jar").toURL(), "1.10.0-SNAPSHOT", mockModules);
		
		assertThat(result, is(true));
	}