@PostLoad
    protected void onLoadJob() throws GeniePreconditionException {
        this.clusterCriterias = this.stringToClusterCriterias(this.clusterCriteriasString);
        this.commandCriteria = this.stringToCommandCriteria(this.commandCriteriaString);
    }