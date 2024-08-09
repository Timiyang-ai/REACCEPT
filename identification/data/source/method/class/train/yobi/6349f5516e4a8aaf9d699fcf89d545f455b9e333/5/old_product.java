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

        for (Label label : labels) {
            label.delete(this);
            label.update();
        }

        for(IssueLabel label : IssueLabel.findByProject(this)) {
            label.delete();
        }

        for(Issue issue : issues) {
            issue.delete();
        }

        for (Assignee assignee : assignees) {
            assignee.delete();
        }

        for(Posting posting : posts) {
            posting.delete();
        }

        for (ProjectVisitation visit :
                ProjectVisitation.find.where().eq("project", this).findList()) {
            visit.delete();
        }

        super.delete();
    }