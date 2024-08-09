@Override
    public void updateCommand(
        @NotBlank(message = "No id entered. Unable to update.")
        final String id,
        @NotNull(message = "No command information entered. Unable to update.")
        @Valid
        final Command updateCommand
    ) throws GenieException {
        if (!this.commandRepo.exists(id)) {
            throw new GenieNotFoundException("No command exists with the given id. Unable to update.");
        }
        if (!id.equals(updateCommand.getId())) {
            throw new GenieBadRequestException("Command id inconsistent with id passed in.");
        }

        log.debug("Called to update command with id {} {}", id, updateCommand);

        final CommandEntity commandEntity = this.findCommand(id);
        commandEntity.setName(updateCommand.getName());
        commandEntity.setUser(updateCommand.getUser());
        commandEntity.setVersion(updateCommand.getVersion());
        commandEntity.setStatus(updateCommand.getStatus());
        commandEntity.setDescription(updateCommand.getDescription());
        commandEntity.setExecutable(updateCommand.getExecutable());
        commandEntity.setSetupFile(updateCommand.getSetupFile());
        commandEntity.setConfigs(updateCommand.getConfigs());
        commandEntity.setTags(updateCommand.getTags());
        this.commandRepo.save(commandEntity);
    }