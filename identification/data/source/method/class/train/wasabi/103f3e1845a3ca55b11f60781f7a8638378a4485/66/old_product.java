@GET
    @Path("/{applicationName}/experiments")
    @Produces(APPLICATION_JSON)
    @ApiOperation(value = "Returns metadata for all experiments within an application",
            response = ExperimentList.class)
    @Timed
    public Response getExperiments(@PathParam("applicationName")
                                   @ApiParam(value = "Application Name")
                                   final Application.Name applicationName,

                                   @HeaderParam(AUTHORIZATION)
                                   @ApiParam(value = EXAMPLE_AUTHORIZATION_HEADER, required = true)
                                   final String authorizationHeader,

                                   @QueryParam("page")
                                   @DefaultValue(DEFAULT_PAGE)
                                   @ApiParam(name = "page", defaultValue = DEFAULT_PAGE, value = EXAMPLE_PAGE)
                                   final int page,

                                   @QueryParam("per_page")
                                   @DefaultValue(DEFAULT_PER_PAGE)
                                   @ApiParam(name = "per_page", defaultValue = DEFAULT_PER_PAGE, value = EXAMPLE_PER_PAGE)
                                   final int perPage,

                                   @QueryParam("sort")
                                   @DefaultValue("")
                                   @ApiParam(name = "sort", defaultValue = "", value = EXAMPLE_SORT)
                                   final String sort,

                                   @QueryParam("filter")
                                   @DefaultValue("")
                                   @ApiParam(name = "filter", defaultValue = "", value = EXAMPLE_SORT)
                                   final String filter,

                                   @QueryParam("timezone")
                                   @DefaultValue("+0000")
                                   @ApiParam(name = "timezone", defaultValue = "+0000", value = EXAMPLE_TIMEZONE)
                                   final String timezoneOffset) {
        List<Experiment> experimentList = authorizedExperimentGetter.getAuthorizedExperimentsByName(authorizationHeader,
                applicationName);

        experimentList = experimentPaginationHelper.paginate(experimentList, filter, timezoneOffset, sort, page, perPage);

        return httpHeader.headers().entity(experimentList).build();
    }