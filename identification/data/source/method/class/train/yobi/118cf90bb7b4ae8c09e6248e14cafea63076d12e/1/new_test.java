    @Test
    public void saveComment() throws Exception {
        // Given
        IMAPMessage message = mock(IMAPMessage.class);
        when(message.getAllRecipients()).thenReturn(new InternetAddress[]{
                new InternetAddress("foo@mail.com")
        });

        when(message.getFrom()).thenReturn(new InternetAddress[]{
                new InternetAddress(member.email)
        });
        when(message.getContentType()).thenReturn("text/plain");
        when(message.isMimeType("text/*")).thenReturn(true);
        when(message.getContent()).thenReturn("body");
        when(message.getMessageID()).thenReturn("<message-id-2@domain>");

        Issue issue = new Issue();
        issue.setProject(project);
        issue.setTitle("hello");
        issue.setBody("world");
        issue.setAuthor(member);
        issue.state = State.OPEN;
        issue.save();

        // When
        Comment comment = CreationViaEmail.saveComment(message, issue.asResource());
        comment.refresh();

        // Then
        assertThat(comment.authorId)
                .describedAs("authorId")
                .isEqualTo(member.id);
        assertThat(comment.contents)
                .describedAs("contents")
                .isEqualTo((String) message.getContent());
        assertThat(comment.getParent().id)
                .describedAs("parent")
                .isEqualTo(issue.id);
    }