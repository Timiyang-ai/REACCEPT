@Override
    public String createApplication(
        @NotNull(message = "No application entered to create.")
        @Valid
        final Application app
    ) throws GenieException {
        log.debug("Called with application: {}", app.toString());
        if (app.getId() != null && this.applicationRepo.exists(app.getId())) {
            throw new GenieConflictException("An application with id " + app.getId() + " already exists");
        }

        final ApplicationEntity applicationEntity = new ApplicationEntity();
        if (StringUtils.isBlank(app.getId())) {
            applicationEntity.setId(UUID.randomUUID().toString());
        } else {
            applicationEntity.setId(app.getId());
        }
        this.updateAndSaveApplicationEntity(applicationEntity, app);
        return applicationEntity.getId();
    }