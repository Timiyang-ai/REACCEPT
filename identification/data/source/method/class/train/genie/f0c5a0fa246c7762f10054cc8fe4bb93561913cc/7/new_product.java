@PrePersist
    @PreUpdate
    protected void onCreateOrUpdateCommand() throws GeniePreconditionException {
        this.addAndValidateSystemTags(this.tags);
    }