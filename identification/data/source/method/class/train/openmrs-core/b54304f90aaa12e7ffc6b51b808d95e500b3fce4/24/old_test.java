	@Test(expected = ModuleException.class)
	public void checkRequiredVersion_shouldThrowModuleExceptionIfOpenmrsVersionBeyondWildCardRange() {
		String openmrsVersion = "1.4.3";
		String requiredOpenmrsVersion = "1.3.*";
		ModuleUtil.checkRequiredVersion(openmrsVersion, requiredOpenmrsVersion);
	}