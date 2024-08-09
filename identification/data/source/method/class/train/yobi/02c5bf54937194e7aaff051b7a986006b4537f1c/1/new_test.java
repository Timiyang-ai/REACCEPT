    @Test
    public void getPullRequestCommits() throws Exception {
        // Given
        PullRequestCommit first = createPullRequestCommit("2013-12-01");
        PullRequestCommit second = createPullRequestCommit("2013-12-02");
        PullRequestCommit third = createPullRequestCommit("2013-12-03");
        String[] ids = {first.id, second.id, third.id};

        PullRequestEvent event = new PullRequestEvent();
        event.newValue = StringUtils.join(ids, PullRequest.DELIMETER);

        // When
        List<PullRequestCommit> commits = event.getPullRequestCommits();

        // Then
        assertThat(commits.get(0).id).isEqualTo(third.id);
        assertThat(commits.get(1).id).isEqualTo(second.id);
        assertThat(commits.get(2).id).isEqualTo(first.id);
    }