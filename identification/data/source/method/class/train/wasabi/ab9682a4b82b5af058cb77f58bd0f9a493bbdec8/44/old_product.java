@GET
    @Produces(APPLICATION_JSON)
    @ApiOperation(value = "Return details of all the experiments, with respect to the authorization",
            response = ExperimentList.class)
    @Timed
    public Response getExperiments(@HeaderParam(AUTHORIZATION)
                                   @ApiParam(value = EXAMPLE_AUTHORIZATION_HEADER, required = true)
                                   final String authorizationHeader) {
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

        return httpHeader.headers().entity(authorizedExperiments).build();
    }