@PostLoad
    protected void onLoadJob() throws GenieException {
        this.clusterCriterias = this.stringToClusterCriterias(this.clusterCriteriasString);
        this.commandCriteria = this.stringToCommandCriteria(this.commandCriteriaString);
    }