@PrePersist
    @PreUpdate
    protected void onCreateOrUpdateCommand() throws GenieException {
        this.setCommandTags(this.getFinalTags());
    }