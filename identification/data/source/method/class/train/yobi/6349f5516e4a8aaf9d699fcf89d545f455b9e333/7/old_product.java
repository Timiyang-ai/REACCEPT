@Override
    public void delete() {
        for (Assignee assignee : assignees) {
            assignee.delete();
        }
        super.delete();
    }