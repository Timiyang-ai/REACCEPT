@POST
    @Consumes(APPLICATION_JSON)
    @ApiOperation(value = "Create an experiment",
            response = Experiment.class)
    @Timed
    public Response postExperiment(@ApiParam(required = true)
                                   final NewExperiment newExperiment,

                                   @QueryParam("createNewApplication")
                                   @DefaultValue("false")
                                   final boolean createNewApplication,

                                   @HeaderParam(AUTHORIZATION)
                                   @ApiParam(value = EXAMPLE_AUTHORIZATION_HEADER, required = true)
                                   final String authorizationHeader) {
        if (newExperiment.getApplicationName() == null || isBlank(newExperiment.getApplicationName().toString())) {
            throw new IllegalArgumentException("Experiment application name cannot be null or an empty string");
        }

        Username userName = authorization.getUser(authorizationHeader);

        if (!createNewApplication) {
            authorization.checkUserPermissions(userName, newExperiment.getApplicationName(), CREATE);
        }

        // Avoid causing breaking change in API request body, derive creatorID from auth headers
        String creatorID = (userName != null) ? userName.getUsername() : null;

        newExperiment.setCreatorID(creatorID);
        experiments.createExperiment(newExperiment, authorization.getUserInfo(userName));

        Experiment experiment = experiments.getExperiment(newExperiment.getID());

        if (createNewApplication) {
            UserRole userRole = newInstance(experiment.getApplicationName(), ADMIN).withUserID(userName).build();

            authorization.setUserRole(userRole, null);
        }

        return httpHeader.headers(CREATED).entity(experiment).build();
    }