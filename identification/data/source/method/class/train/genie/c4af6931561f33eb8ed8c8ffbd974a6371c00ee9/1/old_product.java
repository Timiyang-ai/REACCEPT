@Override
    public Set<Application> setApplicationsForCommand(
            @NotBlank(message = "No command id entered. Unable to add applications.")
            final String id,
            @NotNull(message = "No applications entered. Unable to set applications.")
            final Set<Application> applications
    ) throws GenieException {
        if (applications.size()
                != applications.stream().filter(application -> this.appRepo.exists(application.getId())).count()) {
            throw new GeniePreconditionException("All applications must have an id and exist to add to a command");
        }

        final Command command = this.commandRepo.findOne(id);
        if (command != null) {
            final Set<Application> attachedApplications = new HashSet<>();
            applications.stream().forEach(
                    application -> attachedApplications.add(this.appRepo.findOne(application.getId()))
            );
            command.setApplications(attachedApplications);
            return command.getApplications();
        } else {
            throw new GenieNotFoundException("No command with id " + id + " exists.");
        }
    }