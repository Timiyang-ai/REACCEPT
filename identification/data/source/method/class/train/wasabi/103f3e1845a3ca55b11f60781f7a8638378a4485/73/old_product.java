@GET
    @Produces(APPLICATION_JSON)
    @ApiOperation(value = "Returns all logs if the requesting user has SuperAdmin permissions.",
            notes = "Returns all logs if the requesting user has SuperAdmin permissions. "
                    + "The parameters allow for filtering, sorting, and pagination.",
            response = Response.class,
            httpMethod = "GET",
            produces = "application/json",
            protocols = "https")
    @Timed(name = "getCompleteLogs")
    public Response getCompleteLogs(@HeaderParam(AUTHORIZATION)
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
                                    @ApiParam(name = "filter", defaultValue = "", value = EXAMPLE_FILTER)
                                    final String filter,

                                    @QueryParam("timezone")
                                    @DefaultValue("+0000")
                                    @ApiParam(name = "timezone", defaultValue = "+0000", value = EXAMPLE_TIMEZONE)
                                    final String timezoneOffset) {
        return getLogs(authorizationHeader, null, page, perPage, sort, filter, timezoneOffset);
    }