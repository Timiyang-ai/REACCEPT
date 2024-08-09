@PrePersist
    @PreUpdate
    protected void onCreateOrUpdateJob() throws GeniePreconditionException {
        this.clusterCriteriasString = clusterCriteriasToString(this.clusterCriterias);
        this.commandCriteriaString = commandCriteriaToString(this.commandCriteria);
        // Add the id to the tags
        if (this.tags == null) {
            this.tags = new HashSet<>();
        }
    }