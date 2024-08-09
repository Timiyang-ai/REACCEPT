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
            experimentList = addAllocationPercentToExperimentList(experimentList);
            return httpHeader.headers().entity(experimentList).build();
        } catch (Exception exception) {
            LOGGER.error("getPageExperiments failed for applicationName={}, pageName={} with error:",
                    applicationName, pageName, exception);
            throw exception;
        }
    }