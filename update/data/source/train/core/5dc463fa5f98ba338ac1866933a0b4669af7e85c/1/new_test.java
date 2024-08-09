@Test
	public void testLogin()
	{
		LoginContainer container = new LoginContainer();
		try
		{
			container.login(null);
			fail("LoginContext is required");
		}
		catch (LoginException e)
		{
		}
		LoginContext ctx = new LoginContext(true)
		{
			private static final long serialVersionUID = 1L;

			@Override
			public Subject login()
			{
				DefaultSubject subject = new DefaultSubject();
				subject.addPrincipal(new SimplePrincipal("home"));
				return subject;
			}
		};
		assertFalse(container.isClassAuthenticated(getClass()));
		try
		{
			WicketTester mock = new WicketTester();
			container.login(ctx);
			mock.processRequest();
			mock.destroy();

		}
		catch (LoginException e)
		{
			log.error(e.getMessage(), e);
			fail(e.getMessage());
		}
		assertNotNull(container.getSubject());
		assertTrue(container.isClassAuthenticated(getClass()));
		// shows that even though the new context does not authenticate anything
		// the
		// previous one does
		ctx = new LoginContext(1)
		{
			// bad example, do not create an anonymous Subject in a LoginContext
			// as it will cause the context to be serialized along with the
			// subject
			@Override
			public Subject login()
			{
				return new MyDefaultSubject();
			}

			/**
			 * @see org.wicketstuff.security.hive.authentication.LoginContext#preventsAdditionalLogins()
			 */
			@Override
			public boolean preventsAdditionalLogins()
			{
				return true;
			}
		};
		try
		{
			WicketTester mock = new WicketTester();
			container.login(ctx);
			mock.processRequest();
			mock.destroy();
		}
		catch (LoginException e)
		{
			log.error(e.getMessage(), e);
			fail(e.getMessage());
		}
		assertTrue(container.isClassAuthenticated(getClass()));
		// note changing the order does not matter since the first that
		// authenticates true
		// is used.
	}