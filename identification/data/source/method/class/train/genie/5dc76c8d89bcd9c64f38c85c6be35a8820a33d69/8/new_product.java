@Override
    public void setApplicationsForCommand(
        @NotBlank(message = "No command id entered. Unable to set applications.")
        final String id,
        @NotNull(message = "No application ids entered. Unable to set applications.")
        final List<String> applicationIds
    ) throws GenieException {
        if (applicationIds.size() != applicationIds.stream().filter(this.appRepo::exists).count()) {
            throw new GeniePreconditionException("All applications need to exist to add to a command");
        }

        final CommandEntity commandEntity = this.findCommand(id);
        final List<ApplicationEntity> applicationEntities = new ArrayList<>();
        applicationIds.forEach(appId -> applicationEntities.add(this.appRepo.findOne(appId)));

        commandEntity.setApplications(applicationEntities);
    }