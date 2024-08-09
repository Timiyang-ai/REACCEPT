public void addCommand(@NotNull final CommandEntity command) throws GeniePreconditionException {
        if (this.commands.contains(command)) {
            throw new GeniePreconditionException("A command with id " + command.getUniqueId() + " is already added");
        }
        this.commands.add(command);
        command.getClusters().add(this);
    }