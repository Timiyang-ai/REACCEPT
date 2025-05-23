public void removeAllCommands() throws GenieException {
        if (this.commands != null) {
            final List<Command> locCommands = new ArrayList<Command>();
            locCommands.addAll(this.commands);
            for (final Command command : locCommands) {
                this.removeCommand(command);
            }
        }
    }