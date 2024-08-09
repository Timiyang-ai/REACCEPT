@PrePersist
    @PreUpdate
    protected void onCreateOrUpdateCluster() throws GeniePreconditionException {
        validate(this.status, this.clusterType, null);
        // Add the id to the tags
        if (this.tags == null) {
            this.tags = new HashSet<>();
        }
        this.addAndValidateSystemTags(this.tags);
    }