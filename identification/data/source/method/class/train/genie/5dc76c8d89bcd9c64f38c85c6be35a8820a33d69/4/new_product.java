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
        final Optional<String> updateId = updateCommand.getId();
        if (updateId.isPresent() && !id.equals(updateId.get())) {
            throw new GenieBadRequestException("Command id inconsistent with id passed in.");
        }

        log.debug("Called to update command with id {} {}", id, updateCommand);

        this.updateAndSaveCommandEntity(this.findCommand(id), updateCommand);
    }