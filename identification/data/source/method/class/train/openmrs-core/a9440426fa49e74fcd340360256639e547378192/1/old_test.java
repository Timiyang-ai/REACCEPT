@Test()
	@Verifies(value = "should throw APIException if given empty implementationId object", method = "setImplementationId(ImplementationId)")
	public void setImplementationId_shouldThrowAPIExceptionIfGivenEmptyImplementationIdObject() throws Exception {
		// save a blank impl id. exception thrown
		try {
			adminService.setImplementationId(new ImplementationId());
			fail("An exception should be thrown on a blank impl id save");
		}
		catch (APIException e) {
			// expected exception
		}
		ImplementationId afterBlank = adminService.getImplementationId();
		assertNull("There shouldn't be an impl id defined after setting a blank impl id", afterBlank);
	}