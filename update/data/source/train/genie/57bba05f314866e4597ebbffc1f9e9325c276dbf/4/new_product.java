@Override
    public List<Command> addCommandsForCluster(
            @NotBlank(message = "No cluster id entered. Unable to add commands.")
            final String id,
            @NotEmpty(message = "No commands entered. Unable to add commands.")
            final List<Command> commands
    ) throws GenieException {
        final Cluster cluster = this.clusterRepo.findOne(id);
        if (cluster != null) {
            for (final Command detached : commands) {
                final Command cmd = this.commandRepo.findOne(detached.getId());
                if (cmd != null) {
                    cluster.addCommand(cmd);
                } else {
                    throw new GenieNotFoundException("No command with id " + detached.getId() + " exists.");
                }
            }
            return cluster.getCommands();
        } else {
            throw new GenieNotFoundException("No cluster with id " + id + " exists.");
        }
    }