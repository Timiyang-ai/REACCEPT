@GET
    @Produces(APPLICATION_JSON)
    @ApiOperation(value = "Return details of all the experiments, with respect to the authorization",
            response = ExperimentList.class)
    @Timed
    public Response getExperiments(
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
            ExperimentList experimentList = experiments.getExperiments();
            ExperimentList authorizedExperiments;

            if (authorizationHeader == null) {
                throw new AuthenticationException("No authorization given.");
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

                List<Experiment.ID> favoriteList = favorites.getFavorites(userName);

                authorizedExperiments.getExperiments().
                        parallelStream().filter(experiment -> favoriteList.contains(experiment.getID()))
                        .forEach(experiment -> experiment.setFavorite(true));
            }

            Map<String, Object> experimentResponse = experimentPaginationHelper
                    .paginate("experiments", authorizedExperiments.getExperiments(), filter, timezoneOffset,
                            (perPage != -1 ? "-favorite," : "") + sort, page, perPage);

            return httpHeader.headers().entity(experimentResponse).build();
        } catch (Exception exception) {
            LOGGER.error("getExperiments failed for page={}, perPage={}, "
                            + "filter={}, sort={}, timezoneOffset={} with error:",
                    page, perPage, filter, sort, timezoneOffset,
                    exception);
            throw exception;
        }
    }