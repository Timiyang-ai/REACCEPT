@Override
    public Application updateApplication(
            @NotBlank(message = "No application id entered. Unable to update.")
            final String id,
            @NotNull(message = "No application information entered. Unable to update.")
            @Valid
            final Application updateApp
    ) throws GenieException {
        if (!this.applicationRepo.exists(id)) {
            throw new GenieNotFoundException("No application information entered. Unable to update.");
        }
        if (!id.equals(updateApp.getId())) {
            throw new GenieBadRequestException("Application id inconsistent with id passed in.");
        }

        LOG.debug("Called with app " + updateApp.toString());
        return this.applicationRepo.save(updateApp);
    }