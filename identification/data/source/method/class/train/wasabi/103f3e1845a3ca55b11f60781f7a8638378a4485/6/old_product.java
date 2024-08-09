@GET
    @Path("/applications/{applicationName}/pages/{pageName}")
    @ApiOperation(value = "Return all experiments for page for app",
            response = ExperimentList.class)
    @Timed
    public Response getPageExperiments(
            @PathParam("applicationName")
            @ApiParam(value = "Application Name")
            final Application.Name applicationName,

            @PathParam("pageName")
            @ApiParam(value = "Page name where the experiment will appear")
            final Page.Name pageName) {
        try {
            ExperimentList experimentList = pages.getPageExperiments(applicationName, pageName);
            if (experimentList != null) {
                for (Experiment experiment : experimentList.getExperiments()) {
                    AssignmentCounts assignmentCounts = analytics.getAssignmentCounts(experiment.getID(), null);
                    long nullAssignments = 0;
                    long bucketAssignments = 0;
                    if (assignmentCounts != null) {
                        for (BucketAssignmentCount bucketAssignmentCount : assignmentCounts.getAssignments()) {
                                if (bucketAssignmentCount.getBucket() == null || bucketAssignmentCount.getBucket().toString().equalsIgnoreCase("null")) {
                                    nullAssignments = bucketAssignmentCount.getCount();
                                } else {
                                    bucketAssignments += bucketAssignmentCount.getCount();
                                }
                        }
                        double totalAssignments = nullAssignments + bucketAssignments;
                        if (totalAssignments != 0) {
                            experiment.setAllocationPercent(bucketAssignments / totalAssignments);
                        }
                    }
                }
            }
            return httpHeader.headers().entity(experimentList).build();
        } catch (Exception exception) {
            LOGGER.error("getPageExperiments failed for applicationName={}, pageName={} with error:",
                    applicationName, pageName, exception);
            throw exception;
        }
    }