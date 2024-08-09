@Override
    public String createApplication(
        @NotNull(message = "No application entered to create.")
        @Valid
        final Application app
    ) throws GenieException {
        log.debug("Called with application: {}", app.toString());
        final Optional<String> appId = app.getId();
        if (appId.isPresent() && this.applicationRepo.exists(appId.get())) {
            throw new GenieConflictException("An application with id " + appId.get() + " already exists");
        }

        final ApplicationEntity applicationEntity = new ApplicationEntity();
        applicationEntity.setId(app.getId().orElse(UUID.randomUUID().toString()));
        this.updateAndSaveApplicationEntity(applicationEntity, app);
        return applicationEntity.getId();
    }