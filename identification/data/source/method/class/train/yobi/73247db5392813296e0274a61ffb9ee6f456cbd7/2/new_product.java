@Override
    public List<Commit> getHistory(int pageNumber, int pageSize, String untilRevName)
            throws IOException, GitAPIException {
        // Get the list of commits from HEAD to the given pageNumber.
        LogCommand logCommand = new Git(repository).log();
        if (untilRevName != null) {
            logCommand.add(repository.resolve(untilRevName));
        }
        Iterable<RevCommit> iter = logCommand.setMaxCount(pageNumber * pageSize + pageSize).call();
        List<RevCommit> list = new LinkedList<>();
        for (RevCommit commit : iter) {
            if (list.size() >= pageSize) {
                list.remove(0);
            }
            list.add(commit);
        }

        List<Commit> result = new ArrayList<>();
        for (RevCommit commit : list) {
            result.add(new GitCommit(commit));
        }

        return result;
    }