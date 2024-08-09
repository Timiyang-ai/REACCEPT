@Override
    public void delete() {
        deleteFork();
        deletePullRequests();

        if(this.hasForks()) {
            for(Project fork : forkingProjects) {
                fork.deletePullRequests();
                fork.deleteOriginal();
                fork.update();
            }
        }

        for (Assignee assignee : assignees) {
            assignee.delete();
        }
        super.delete();
    }