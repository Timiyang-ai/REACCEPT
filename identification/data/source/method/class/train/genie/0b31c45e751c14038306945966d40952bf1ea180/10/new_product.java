public void removeCommand(final Command command)
            throws GeniePreconditionException {
        if (command == null) {
            throw new GeniePreconditionException("No command entered unable to remove.");
        }

        if (this.commands != null) {
            this.commands.remove(command);
        }
        if (command.getClusters() != null) {
            command.getClusters().remove(this);
        }
    }