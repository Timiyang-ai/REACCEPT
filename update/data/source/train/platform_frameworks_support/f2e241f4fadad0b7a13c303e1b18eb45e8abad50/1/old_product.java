@NonNull
    public ListenableFuture<SessionResult> sendCustomCommand(@NonNull SessionCommand command,
            @Nullable Bundle args) {
        if (command == null) {
            throw new IllegalArgumentException("command shouldn't be null");
        }
        if (command.getCommandCode() != SessionCommand.COMMAND_CODE_CUSTOM) {
            throw new IllegalArgumentException("command should be a custom command");
        }
        if (isConnected()) {
            return getImpl().sendCustomCommand(command, args);
        }
        return createDisconnectedFuture();
    }