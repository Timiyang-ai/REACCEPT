@GET
    @Produces(APPLICATION_JSON)
    @ApiOperation(value = "Return details of all the experiments, with respect to the authorization",
            response = ExperimentList.class)
    @Timed
    public Response getExperiments(@HeaderParam(AUTHORIZATION)
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
                                       final String timezoneOffset
                                   ) {
        ExperimentList experimentList = experiments.getExperiments();
        ExperimentList authorizedExperiments;

        if (authorizationHeader == null) {
            authorizedExperiments = experimentList;
        } else {
            Username userName = authorization.getUser(authorizationHeader);
            Set<Application.Name> allowed = new HashSet<>();

            authorizedExperiments = new ExperimentList();

            for (Experiment experiment : experimentList.getExperiments()) {
                if (experiment == null) {
                    continue;
                }

                Application.Name applicationName = experiment.getApplicationName();

                if (allowed.contains(applicationName)) {
                    authorizedExperiments.addExperiment(experiment);
                } else {
                    try {
                        authorization.checkUserPermissions(userName, applicationName, READ);
                        authorizedExperiments.addExperiment(experiment);
                        allowed.add(applicationName);
                    } catch (AuthenticationException ignored) {
                        LOGGER.trace("ignoring authentication exception", ignored);
                    }
                }
            }
        }

        authorizedExperiments.setExperiments(
                paginationHelper.paginate(authorizedExperiments.getExperiments(), filter, timezoneOffset, sort, page, perPage)
        );

        return httpHeader.headers().entity(authorizedExperiments).build();
    }