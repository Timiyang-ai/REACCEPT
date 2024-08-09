@Test
    public void testAbort() throws SQLException, AuthorizeException
    {
        new NonStrictExpectations(authorizeService.getClass())
        {{
            // Allow Admin permissions - needed to create a new EPerson
            authorizeService.isAdmin((Context) any); result = true;
        }};

        // To test abort() we need a new Context object
        Context instance = new Context();
        
        // Create a new EPerson (DO NOT COMMIT IT)
        String createdEmail = "susie@email.com";
        EPerson newUser = ePersonService.create(instance);
        newUser.setFirstName(context, "Susan");
        newUser.setLastName(context, "Doe");
        newUser.setEmail(createdEmail);
        newUser.setCanLogIn(true);
        newUser.setLanguage(context, I18nUtil.getDefaultLocale().getLanguage());
        
        // Abort our context
        instance.abort();
        // Ensure the context is no longer valid
        assertThat("testAbort 0", instance.isValid(), equalTo(false));
        
        // Open a new context, let's make sure that EPerson isn't there
        Context newInstance = new Context();
        EPerson found = ePersonService.findByEmail(newInstance, createdEmail);
        assertThat("testAbort 1", found, nullValue());

        // Cleanup our contexts
        cleanupContext(instance);
        cleanupContext(newInstance);
    }