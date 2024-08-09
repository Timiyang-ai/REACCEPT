    @Test
    public void createPriorities() throws Exception {
        when(authorization.getUser("foo")).thenReturn(username);
        when(httpHeader.headers(NO_CONTENT)).thenReturn(responseBuilder);
        when(responseBuilder.build()).thenReturn(response);

        applicationsResource.createPriorities(applicationName, experimentIDList, "foo");

        verify(authorization).getUser("foo");
        verify(authorization).checkUserPermissions(username, applicationName, UPDATE);
        verify(priorities).createPriorities(applicationName, experimentIDList, true);
        verify(httpHeader).headers(NO_CONTENT);
        verify(responseBuilder, times(0)).entity(anyObject());
        verify(responseBuilder).build();
    }