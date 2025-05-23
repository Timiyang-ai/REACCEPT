@Test
    @Verifies(value = "should lockout user after eight failed attempts", method = "authenticate(String,String)")
    public void authenticate_shouldLockoutUserAfterEightFailedAttempts() throws Exception {
		// logout after the base setup
		Context.logout();
		
		// we rely on being able to log in as admin/test in this unittest
		// we must do the "improper" try/catch block here because the whole 
		// test is expected to throw and exception at the end
		try {
			dao.authenticate("admin", "test");
		}
		catch (ContextAuthenticationException authException) {
			Assert.fail("There must be an admin:test user for this test to run properly");
		}
		Context.logout();
		
		for (int x = 1; x <= 7; x++) {
			// try to authenticate with a proper 
			try {
				dao.authenticate("admin", "not the right password");
				Assert.fail("Not sure why this username/password combo worked");
			}
			catch (ContextAuthenticationException authException) {
				// pass
			}
		}
		
		// those were the first seven, now the eigth request 
		// (with the same user and right pw) should fail
		dao.authenticate("admin", "test");
	}