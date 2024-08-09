@PostPersist
    @PostUpdate
    protected void onCreateOrUpdateCluster() throws GeniePreconditionException {
        this.addAndValidateSystemTags(this.tags);
    }