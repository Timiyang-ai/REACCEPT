@Override
    public Set<Application> setApplicationsForCommand(
            @NotBlank(message = "No command id entered. Unable to add applications.")
            final String id,
            @NotNull(message = "No application ids entered. Unable to set applications.")
            final Set<String> applicationIds
    ) throws GenieException {
        if (applicationIds.size() != applicationIds.stream().filter(this.appRepo::exists).count()) {
            throw new GeniePreconditionException("All applications exist to add to a command");
        }

        final Command command = this.commandRepo.findOne(id);
        if (command != null) {
            final Set<Application> attachedApplications = new HashSet<>();
            applicationIds.stream().forEach(
                    applicationId -> attachedApplications.add(this.appRepo.findOne(applicationId))
            );
            command.setApplications(attachedApplications);
            return command.getApplications();
        } else {
            throw new GenieNotFoundException("No command with id " + id + " exists.");
        }
    }