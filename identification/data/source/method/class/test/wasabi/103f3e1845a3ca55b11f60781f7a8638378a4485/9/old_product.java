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
                                   @ApiParam(name = "page", defaultValue = DEFAULT_PAGE, value = DOC_PAGE)
                                   final int page,

                                   @QueryParam("per_page")
                                   @DefaultValue(DEFAULT_PER_PAGE)
                                   @ApiParam(name = "per_page", defaultValue = DEFAULT_PER_PAGE, value = DOC_PER_PAGE)
                                   final int perPage,

                                   @QueryParam("filter")
                                   @DefaultValue("")
                                   @ApiParam(name = "filter", defaultValue = DEFAULT_FILTER, value = DOC_FILTER)
                                   final String filter,

                                   @QueryParam("sort")
                                   @DefaultValue("")
                                   @ApiParam(name = "sort", defaultValue = DEFAULT_SORT, value = DOC_SORT)
                                   final String sort,

                                   @QueryParam("timezone")
                                   @DefaultValue(DEFAULT_TIMEZONE)
                                   @ApiParam(name = "timezone", defaultValue = DEFAULT_TIMEZONE, value = DOC_TIMEZONE)
                                   final String timezoneOffset) {
        List<Experiment> experimentList = authorizedExperimentGetter.getAuthorizedExperimentsByName(authorizationHeader,
                applicationName);

        Map<String, Object> response = experimentPaginationHelper.paginate("experiments", experimentList, filter, timezoneOffset, sort, page, perPage);

        return httpHeader.headers().entity(response).build();
    }