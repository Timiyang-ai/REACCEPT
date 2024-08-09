@GET
    @Produces(APPLICATION_JSON)
    @ApiOperation(value = "Returns all logs if the requesting user has SuperAdmin permissions.",
            notes = "Returns all logs if the requesting user has SuperAdmin permissions. "
                    + "The parameters allow for filtering, sorting, and pagination.",
            response = Response.class,
            httpMethod = "GET",
            produces = "application/json",
            protocols = "https")
    @Timed(name = "getLogsForAllApplications")
    public Response getLogsForAllApplications(
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
        try {
            return getLogsForApplication(authorizationHeader, null, page, perPage,
                    filter, sort, timezoneOffset);
        } catch (Exception exception) {
            LOGGER.error("getLogsForAllApplications failed for page:{}, perPage={}, filter={},"
                    + " sort={}, timezoneOffset={} with error:",
                    page, perPage, filter, sort, timezoneOffset,
                    exception);
            throw exception;
        }
    }