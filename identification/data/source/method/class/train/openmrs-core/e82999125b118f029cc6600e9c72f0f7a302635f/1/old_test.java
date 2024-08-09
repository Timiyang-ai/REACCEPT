	@Test
	public void getCallerClass_shouldGetTheMostRecentlyCalledMethod() {
		OpenmrsSecurityManager openmrsSecurityManager = new OpenmrsSecurityManager();
		Class<?> callerClass = openmrsSecurityManager.getCallerClass(0);
		Assert.assertTrue("Oops, didn't get a junit type of class: " + callerClass, callerClass.getPackage().getName()
		        .contains("junit"));
	}