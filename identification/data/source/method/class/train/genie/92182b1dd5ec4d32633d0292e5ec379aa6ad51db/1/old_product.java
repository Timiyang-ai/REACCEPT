@PrePersist
    @PreUpdate
    protected void onCreateOrUpdateJob() throws GeniePreconditionException {
        this.validate(this.commandCriteria, this.commandArgs, this.clusterCriterias, null);
        this.clusterCriteriasString = clusterCriteriasToString(this.clusterCriterias);
        this.commandCriteriaString = commandCriteriaToString(this.commandCriteria);
        // Add the id to the tags
        if (this.tags == null) {
            this.tags = new HashSet<>();
        }
        this.addAndValidateSystemTags(this.tags);
    }