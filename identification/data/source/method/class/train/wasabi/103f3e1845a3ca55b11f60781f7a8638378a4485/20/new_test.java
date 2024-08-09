    @Test
    public void postFeedback() throws Exception {
        when(authorization.getUser("foo")).thenReturn(username);
        when(httpHeader.headers(CREATED)).thenReturn(responseBuilder);
        when(responseBuilder.build()).thenReturn(response);

        feedbackResource.postFeedback(userFeedback, "foo");

        verify(authorization).getUser("foo");
        verify(userFeedback).setUsername(username);
        verify(feedback).createUserFeedback(userFeedback);
        verify(httpHeader).headers(CREATED);
        verify(responseBuilder, times(0)).entity(anyObject());
        verify(responseBuilder).build();
    }