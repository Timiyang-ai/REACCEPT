@Override
    //TODO: Shares a lot of code with the add, should be able to refactor
    public List<Command> updateCommandsForCluster(
            @NotBlank(message = "No cluster id entered. Unable to update commands.")
            final String id,
            @NotEmpty(message = "No commands entered. Unable to add commands.")
            final List<Command> commands
    ) throws GenieException {
        final Cluster cluster = this.clusterRepo.findOne(id);
        if (cluster != null) {
            final List<Command> cmds = new ArrayList<>();
            for (final Command detached : commands) {
                final Command cmd = this.commandRepo.findOne(detached.getId());
                if (cmd != null) {
                    cmds.add(cmd);
                } else {
                    throw new GenieNotFoundException("No command with id " + detached.getId() + " exists.");
                }
            }
            cluster.setCommands(cmds);
            return cluster.getCommands();
        } else {
            throw new GenieNotFoundException("No cluster with id " + id + " exists.");
        }
    }