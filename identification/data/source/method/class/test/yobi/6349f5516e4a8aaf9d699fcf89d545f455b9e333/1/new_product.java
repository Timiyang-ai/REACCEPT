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

        for (Label label : labels) {
            label.delete(this);
            label.update();
        }

        for(IssueLabel label : IssueLabel.findByProject(this)) {
            label.delete();
        }

        super.delete();
    }