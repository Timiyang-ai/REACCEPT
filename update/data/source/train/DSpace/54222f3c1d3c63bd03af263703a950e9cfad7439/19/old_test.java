@Test
    public void testAbort() throws SQLException, AuthorizeException
    {
        new NonStrictExpectations(AuthorizeManager.class)
        {{
            // Allow Admin permissions - needed to create a new EPerson
            AuthorizeManager.isAdmin((Context) any); result = true;
        }};
        
        // To test abort() we need a new Context object
        Context instance = new Context();
        
        // Create a new EPerson (DO NOT COMMIT IT)
        String createdEmail = "susie@email.com";
        EPerson newUser = EPerson.create(instance);
        newUser.setFirstName("Susan");
        newUser.setLastName("Doe");
        newUser.setEmail(createdEmail);
        newUser.setCanLogIn(true);
        newUser.setLanguage(I18nUtil.getDefaultLocale().getLanguage());
        
        // Abort our context
        instance.abort();
        // Ensure the context is no longer valid
        assertThat("testAbort 0", instance.isValid(), equalTo(false));
        
        // Open a new context, let's make sure that EPerson isn't there
        Context newInstance = new Context();
        EPerson found = EPerson.findByEmail(newInstance, createdEmail);
        assertThat("testAbort 1", found, nullValue());

        // Cleanup our contexts
        cleanupContext(instance);
        cleanupContext(newInstance);
    }