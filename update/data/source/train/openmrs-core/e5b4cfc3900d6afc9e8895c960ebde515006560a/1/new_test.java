@Test(expected = ContextAuthenticationException.class)
    @Verifies(value = "should pass regression test for 1580", method = "authenticate(String,String)")
    public void authenticate_shouldPassRegressionTestFor1580() throws Exception {
		// logout after the base setup
		Context.logout();
		
		// first we fail a login attempt
		try {
			dao.authenticate("admin", "not the right password");
			Assert.fail("Not sure why this username/password combo worked");
		} catch (ContextAuthenticationException authException) {
			// pass
		}
		
		// next we log in correctly
		try {
			dao.authenticate("admin", "test");
		}
		catch (ContextAuthenticationException authException) {
			Assert.fail("There must be an admin:test user for this test to run properly");
		}
		Context.logout();
		
		for (int x = 1; x <= 8; x++) {
			// now we fail several login attempts 
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