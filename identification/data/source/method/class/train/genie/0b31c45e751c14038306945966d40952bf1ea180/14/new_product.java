public void removeAllCommands() throws GeniePreconditionException {
        if (this.commands != null) {
            final List<Command> locCommands = new ArrayList<>();
            locCommands.addAll(this.commands);
            for (final Command command : locCommands) {
                this.removeCommand(command);
            }
        }
    }