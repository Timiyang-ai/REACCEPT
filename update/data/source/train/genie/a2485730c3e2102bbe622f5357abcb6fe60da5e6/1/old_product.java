@Override
    public void addApplicationsForCommand(
        @NotBlank(message = "No command id entered. Unable to add applications.")
        final String id,
        @NotEmpty(message = "No application ids entered. Unable to add applications.")
        final Set<String> applicationIds
    ) throws GenieException {
        if (applicationIds.size() != applicationIds.stream().filter(this.appRepo::exists).count()) {
            throw new GeniePreconditionException("All applications need to exist to add to a command");
        }

        final CommandEntity commandEntity = this.findCommand(id);
        applicationIds.stream().forEach(
            applicationId -> commandEntity.getApplications().add(this.appRepo.findOne(applicationId))
        );
    }