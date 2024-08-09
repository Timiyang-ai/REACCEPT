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

        for (Tag tag : tags) {
            tag.delete(this);
            tag.update();
        }

        for(IssueLabel label : IssueLabel.findByProject(this)) {
            label.delete();
        }

        super.delete();
    }