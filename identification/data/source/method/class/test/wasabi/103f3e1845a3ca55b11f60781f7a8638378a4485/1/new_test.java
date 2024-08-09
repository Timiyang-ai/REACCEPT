    @Test
    public void getUserFeedback() throws Exception {
        when(authorization.getUser("foo")).thenReturn(username);
        when(feedback.getUserFeedback(username)).thenReturn(userFeedbacks);
        when(httpHeader.headers()).thenReturn(responseBuilder);
        when(responseBuilder.entity(anyCollection())).thenReturn(responseBuilder);
        when(responseBuilder.build()).thenReturn(response);

        feedbackResource.getUserFeedback(username, "foo");

        verify(authorization).getUser("foo");
        verify(authorization).checkSuperAdmin(username);
        verify(feedback).getUserFeedback(username);
        verify(httpHeader).headers();
        verify(responseBuilder).entity(userFeedbackCaptor.capture());
        assertThat(userFeedbackCaptor.getValue().size(), is(1));
        assertThat(userFeedbackCaptor.getValue(), hasEntry("feedbackList", userFeedbacks));
        verify(responseBuilder).build();
    }