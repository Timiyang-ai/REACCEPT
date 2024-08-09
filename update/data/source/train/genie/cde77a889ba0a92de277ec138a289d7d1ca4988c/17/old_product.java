@Override
    public Application createApplication(
            @NotNull(message = "No application entered to create.")
            @Valid
            final Application app) throws GenieException {
        LOG.debug("Called with application: " + app.toString());
        if (app.getId() != null && this.applicationRepo.exists(app.getId())) {
            throw new GenieConflictException("An application with id " + app.getId() + " already exists");
        }
        return this.applicationRepo.save(app);
    }