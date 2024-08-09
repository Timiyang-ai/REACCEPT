void setCommands(@Nullable final Set<CommandEntity> commands) {
        this.commands.clear();
        if (commands != null) {
            this.commands.addAll(commands);
        }
    }