    @Test
    public void convertRawUriToRegex() {
        
        assertThat(
                Route.convertRawUriToRegex("/me/{username: .*}"), 
                CoreMatchers.equalTo("/me/(.*)")); 
        
        assertThat(
                Route.convertRawUriToRegex("/me/{username: [a-zA-Z][a-zA-Z_0-9]}"), 
                CoreMatchers.equalTo("/me/([a-zA-Z][a-zA-Z_0-9])")); 
        
        assertThat(
                Route.convertRawUriToRegex("/me/{username}"), 
                CoreMatchers.equalTo("/me/([^/]*)")); 

        // check regex with escapes/backslashes (\)
        assertThat(
                Route.convertRawUriToRegex("/me/{id: \\d+}"),
                CoreMatchers.equalTo("/me/(\\d+)"));

        // check regex with groups, they should be converted to non-capturing groups
        // people may want to have both "/users/mike" and "/mike" in one route
        // https://github.com/ninjaframework/ninja/issues/497
        assertThat(
                Route.convertRawUriToRegex("(/users)?/{user}"),
                CoreMatchers.equalTo("(?:/users)?/([^/]*)")
        );
    }