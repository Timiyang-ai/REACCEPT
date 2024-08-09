@Override
    public List<Commit> getHistory(int page, int limit, String until)
            throws AmbiguousObjectException, IOException, NoHeadException, GitAPIException {
        // Get the list of commits from HEAD to the given page.
        LogCommand logCommand = new Git(repository).log();
        if (until != null) {
            logCommand.add(repository.resolve(until));
        }
        Iterable<RevCommit> iter = logCommand.setMaxCount(page * limit + limit).call();
        List<RevCommit> list = new LinkedList<RevCommit>();
        for (RevCommit commit : iter) {
            if (list.size() >= limit) {
                list.remove(0);
            }
            list.add(commit);
        }

        List<Commit> result = new ArrayList<Commit>();
        for (RevCommit commit : list) {
            result.add(new GitCommit(commit));
        }

        return result;
    }