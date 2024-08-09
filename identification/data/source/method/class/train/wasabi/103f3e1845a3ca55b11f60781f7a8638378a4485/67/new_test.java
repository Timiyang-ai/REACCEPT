    @Test
    public void deleteUserRoles() throws Exception {

        AuthorizationResource authorizationResource = new AuthorizationResource(authorization, new HttpHeader("jaba-???", "600"));

        when(authorization.getUser(AUTHHEADER)).thenReturn(USER);

        Response response = authorizationResource.deleteUserRoles(TESTAPP, TESTUSER, AUTHHEADER);
        assertThat(response.getStatus(), CoreMatchers.<Object>equalTo(204));
    }