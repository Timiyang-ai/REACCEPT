@Test
	void login()
	{
		tester.startPage(LOGIN);
		tester.assertRenderedPage(LOGIN);
		FormTester form = tester.newFormTester("signInPanel:signInForm");
		form.setValue("username", "test");
		form.setValue("password", "test");
		form.submit();
		tester.assertRenderedPage(HOME);
	}