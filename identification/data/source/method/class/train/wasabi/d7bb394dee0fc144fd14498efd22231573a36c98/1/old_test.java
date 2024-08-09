    @Test
    public void flushMessages() throws Exception {
        when(authorization.getUser(AUTHHEADER)).thenReturn(USER);
        assertThat(resource.flushMessages(AUTHHEADER).getStatus(), is(HttpStatus.SC_NO_CONTENT));
    }