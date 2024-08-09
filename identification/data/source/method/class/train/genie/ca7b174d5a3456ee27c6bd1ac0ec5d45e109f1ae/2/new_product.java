public void setCommands(final List<CommandEntity> commands) {
        //Clear references to this cluster in existing commands
        for (final CommandEntity command : this.commands) {
            command.getClusters().remove(this);
        }

        this.commands.clear();
        if (commands != null) {
            this.commands.addAll(commands);
        }

        //Add the reference in the new commands
        for (final CommandEntity command : this.commands) {
            command.getClusters().add(this);
        }
    }