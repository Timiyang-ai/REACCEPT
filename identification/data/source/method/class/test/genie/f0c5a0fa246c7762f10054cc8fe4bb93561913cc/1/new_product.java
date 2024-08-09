@PrePersist
    @PreUpdate
    protected void onCreateOrUpdateCluster() throws GeniePreconditionException {
        this.addAndValidateSystemTags(this.tags);
    }