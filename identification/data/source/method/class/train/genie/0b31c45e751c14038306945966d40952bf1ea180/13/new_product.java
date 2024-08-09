@PrePersist
    @PreUpdate
    protected void onCreateOrUpdateCluster() throws GeniePreconditionException {
        validate(this.status, this.clusterType, this.configs, null);
        // Add the id to the tags
        if (this.tags == null) {
            this.tags = new HashSet<>();
        }
        this.tags.add(this.getId());
        this.tags.add(this.getName());
    }