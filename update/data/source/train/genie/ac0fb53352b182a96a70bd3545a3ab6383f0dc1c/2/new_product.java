@PrePersist
    @PreUpdate
    protected void onCreateOrUpdateCluster() throws GenieException {
        this.setClusterTags(this.getFinalTags());
    }