@Override
    public String createCommand(
        @NotNull(message = "No command entered. Unable to create.")
        @Valid
        final Command command
    ) throws GenieException {
        log.debug("Called to create command {}", command);
        if (StringUtils.isNotBlank(command.getId()) && this.commandRepo.exists(command.getId())) {
            throw new GenieConflictException("A command with id " + command.getId() + " already exists");
        }

        final CommandEntity commandEntity = new CommandEntity();
        commandEntity.setId(StringUtils.isBlank(command.getId()) ? UUID.randomUUID().toString() : command.getId());
        this.updateAndSaveCommandEntity(commandEntity, command);
        return commandEntity.getId();
    }