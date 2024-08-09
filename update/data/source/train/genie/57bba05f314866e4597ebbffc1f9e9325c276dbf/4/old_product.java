@Override
    public List<Command> addCommandsForCluster(
            final String id,
            final List<Command> commands) throws GenieException {
        if (StringUtils.isBlank(id)) {
            throw new GeniePreconditionException("No cluster id entered. Unable to add commands.");
        }
        if (commands == null) {
            throw new GeniePreconditionException("No commands entered. Unable to add commands.");
        }
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