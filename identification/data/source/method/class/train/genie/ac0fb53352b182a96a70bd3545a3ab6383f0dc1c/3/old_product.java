@PostPersist
    @PostUpdate
    protected void onCreateOrUpdateCommand() throws GeniePreconditionException {
        this.addAndValidateSystemTags(this.tags);
    }