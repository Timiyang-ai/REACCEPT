public void addCommand(@NotNull final CommandEntity command) throws GeniePreconditionException {
        if (
            this.commands
                .stream()
                .map(CommandEntity::getId)
                .filter(id -> id.equals(command.getId()))
                .count() != 0
            ) {
            throw new GeniePreconditionException("A command with id " + command.getId() + " is already added");
        }
        this.commands.add(command);
        command.getClusters().add(this);
    }