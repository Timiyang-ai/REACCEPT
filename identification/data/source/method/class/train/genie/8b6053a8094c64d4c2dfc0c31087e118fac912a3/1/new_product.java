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
        commandEntity.setName(command.getName());
        commandEntity.setUser(command.getUser());
        commandEntity.setVersion(command.getVersion());
        commandEntity.setDescription(command.getDescription());
        commandEntity.setExecutable(command.getExecutable());
        commandEntity.setCheckDelay(command.getCheckDelay());
        commandEntity.setConfigs(command.getConfigs());
        commandEntity.setSetupFile(command.getSetupFile());
        commandEntity.setStatus(command.getStatus());
        commandEntity.setTags(command.getTags());

        return this.commandRepo.save(commandEntity).getId();
    }