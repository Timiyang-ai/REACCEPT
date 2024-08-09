@Override
    public void delete() {
        deleteFork();
        deletePullRequests();

        for (Assignee assignee : assignees) {
            assignee.delete();
        }
        super.delete();
    }