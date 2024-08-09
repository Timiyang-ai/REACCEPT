@PrePersist
    @PreUpdate
    protected void onCreateOrUpdateJob() throws GenieException {
        this.validate(this.commandCriteria, this.commandArgs, this.clusterCriterias, null);
        this.clusterCriteriasString = clusterCriteriasToString(this.clusterCriterias);
        this.commandCriteriaString = commandCriteriaToString(this.commandCriteria);
        // Add the id to the tags
        if (this.tags == null) {
            this.tags = new HashSet<>();
        }
        this.tags.add(this.getId());
        this.tags.add(this.getName());
    }