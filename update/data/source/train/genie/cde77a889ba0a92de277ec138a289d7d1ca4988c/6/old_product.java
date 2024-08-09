@Override
    @Transactional(readOnly = true)
    public Command getCommand(
            @NotBlank(message = "No id entered unable to get.")
            final String id
    ) throws GenieException {
        LOG.debug("called");
        final Command command = this.commandRepo.findOne(id);
        if (command != null) {
            return command;
        } else {
            throw new GenieNotFoundException("No command with id " + id + " exists.");
        }
    }