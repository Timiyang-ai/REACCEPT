List<Command> updateCommandsForCluster(
            @NotBlank(message = "No cluster id entered. Unable to update commands.")
            final String id,
            @NotEmpty(message = "No commands entered. Unable to add commands.")
            final List<Command> commands
    ) throws GenieException;