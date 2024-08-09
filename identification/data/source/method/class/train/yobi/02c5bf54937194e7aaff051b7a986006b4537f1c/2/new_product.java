@Transient
    public List<PullRequestCommit> getPullRequestCommits() {
        List<PullRequestCommit> commits = new ArrayList<PullRequestCommit>();

        String[] commitIds = this.newValue.split(PullRequest.DELIMETER);
        for (String commitId: commitIds) {
            commits.add(PullRequestCommit.findById(commitId));
        }

        Collections.sort(commits, TimelineItem.DESC);

        return commits;
    }