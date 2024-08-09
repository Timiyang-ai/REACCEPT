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

        // Issues must be deleted before issue labels because issues may refer
        // issue labels.
        for(Issue issue : issues) {
            issue.delete();
        }

        for(IssueLabelCategory category : IssueLabelCategory.findByProject(this)) {
            category.delete();
        }

        for (Assignee assignee : assignees) {
            assignee.delete();
        }

        for (Webhook webhook : webhooks) {
            webhook.delete();
        }

        for(Posting posting : posts) {
            posting.delete();
        }

        super.delete();
    }