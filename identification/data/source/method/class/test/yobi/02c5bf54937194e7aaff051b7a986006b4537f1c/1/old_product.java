public List<PullRequestCommit> getPullRequestCommits() {
        List<PullRequestCommit> commits = new ArrayList<PullRequestCommit>();
        
        String[] commitIds = this.newValue.split(COMMIT_ID_DELIMITER);
        for (String commitId: commitIds) {
            commits.add(PullRequestCommit.findById(commitId));
        }
        
        return commits;
    }