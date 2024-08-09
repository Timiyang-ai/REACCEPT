@Override
    public String createCommand(
        @NotNull(message = "No command entered. Unable to create.")
        @Valid
        final Command command
    ) throws GenieException {
        log.debug("Called to create command {}", command);
        final Optional<String> commandId = command.getId();
        if (commandId.isPresent() && this.commandRepo.exists(commandId.get())) {
            throw new GenieConflictException("A command with id " + commandId.get() + " already exists");
        }

        final CommandEntity commandEntity = new CommandEntity();
        commandEntity.setId(command.getId().orElse(UUID.randomUUID().toString()));
        this.updateAndSaveCommandEntity(commandEntity, command);
        return commandEntity.getId();
    }