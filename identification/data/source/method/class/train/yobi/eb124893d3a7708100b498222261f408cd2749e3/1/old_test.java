    @Test
    public void getTimeline() throws Exception {
        // Given
        IssueComment comment1 = createIssueComment("2013-12-01");
        IssueComment comment2 = createIssueComment("2013-12-03");
        List<IssueComment> comments = new ArrayList<>();
        comments.add(comment1);
        comments.add(comment2);
        issue.comments = comments;

        IssueEvent event1 = createIssueEvent("2013-12-02");
        IssueEvent event2 = createIssueEvent("2013-12-04");
        List<IssueEvent> events = new ArrayList<>();
        events.add(event1);
        events.add(event2);
        issue.events = events;

        // When
        List<TimelineItem> timeline = issue.getTimeline();

        // Then
        assertThat(timeline).containsExactly(comment1, event1, comment2, event2);
    }