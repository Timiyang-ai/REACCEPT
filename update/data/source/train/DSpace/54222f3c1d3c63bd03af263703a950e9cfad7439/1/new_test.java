@Test
    @SuppressWarnings("ObjectEqualsNull")
    public void testEquals() throws SQLException, AuthorizeException
    {
        new NonStrictExpectations(authorizeService.getClass())
        {{
            // Allow full Admin perms (just to create top-level community)
            authorizeService.isAdmin((Context) any); result = true;
        }};

        assertFalse("testEquals 0",c.equals(null));
        assertFalse("testEquals 1",c.equals(communityService.create(null, context)));
        assertTrue("testEquals 2", c.equals(c));
    }