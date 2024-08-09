@Override
    public Command createCommand(
            @NotNull(message = "No command entered. Unable to create.")
            @Valid
            final Command command
    ) throws GenieException {
        LOG.debug("Called to create command " + command.toString());
        if (StringUtils.isEmpty(command.getId())) {
            command.setId(UUID.randomUUID().toString());
        }
        if (this.commandRepo.exists(command.getId())) {
            throw new GenieConflictException("A command with id " + command.getId() + " already exists");
        }

        return this.commandRepo.save(command);
    }