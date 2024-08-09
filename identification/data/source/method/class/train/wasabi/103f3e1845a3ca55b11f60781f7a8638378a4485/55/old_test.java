    @Test
    public void getPagesForApplication() throws Exception {
        when(authorization.getUser("foo")).thenReturn(username);
        when(pages.getPageList(applicationName)).thenReturn(pagesByName);
        when(httpHeader.headers()).thenReturn(responseBuilder);
        when(responseBuilder.entity(anyCollection())).thenReturn(responseBuilder);
        when(responseBuilder.build()).thenReturn(response);

        applicationsResource.getPagesForApplication(applicationName, "foo");

        verify(authorization).getUser("foo");
        verify(authorization).checkUserPermissions(username, applicationName, READ);
        verify(pages).getPageList(applicationName);
        verify(responseBuilder).entity(pagesByNameCaptor.capture());
        assertThat(pagesByNameCaptor.getValue().size(), is(1));
        assertThat(pagesByNameCaptor.getValue(), hasEntry("pages", pagesByName));
    }