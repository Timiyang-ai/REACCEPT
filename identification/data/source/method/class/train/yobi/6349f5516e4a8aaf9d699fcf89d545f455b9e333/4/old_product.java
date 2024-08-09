@Override
    public void delete() {
        deleteProjectVisitations();
        deleteProjectTransfer();
        deleteFork();
        deleteCommentThreads();
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

        for(IssueLabelCategory category : IssueLabelCategory.findByProject(this)) {
            category.delete();
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

        super.delete();
    }