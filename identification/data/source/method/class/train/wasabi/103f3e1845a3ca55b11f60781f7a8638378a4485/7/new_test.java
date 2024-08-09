    @Test
    public void getAllUserFeedback() throws Exception {
        when(authorization.getUser("foo")).thenReturn(username);
        when(feedback.getAllUserFeedback()).thenReturn(userFeedbacks);
//        whenHttpHeader(anyCollection());
        when(httpHeader.headers()).thenReturn(responseBuilder);
        when(responseBuilder.entity(anyCollection())).thenReturn(responseBuilder);
        when(responseBuilder.build()).thenReturn(response);

        feedbackResource.getAllUserFeedback("foo");

        verify(authorization).getUser("foo");
        verify(authorization).checkSuperAdmin(username);
        verify(feedback).getAllUserFeedback();
        verify(httpHeader).headers();
        verify(responseBuilder).entity(userFeedbackCaptor.capture());
        assertThat(userFeedbackCaptor.getValue().size(), is(1));
        assertThat(userFeedbackCaptor.getValue(), hasEntry("feedback", userFeedbacks));
        verify(responseBuilder).build();
    }