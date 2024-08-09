@PostPersist
    @PostUpdate
    protected void onCreateOrUpdateApplication() throws GeniePreconditionException {
        this.addAndValidateSystemTags(this.tags);
    }