@Override
    //TODO: Shares a lot of code with the add, should be able to refactor
    public List<Command> updateCommandsForCluster(
            final String id,
            final List<Command> commands) throws GenieException {
        if (StringUtils.isBlank(id)) {
            throw new GenieException(
                    HttpURLConnection.HTTP_PRECON_FAILED,
                    "No cluster id entered. Unable to update commands.");
        }
        if (commands == null) {
            throw new GenieException(
                    HttpURLConnection.HTTP_PRECON_FAILED,
                    "No commands entered. Unable to add commands.");
        }
        final Cluster cluster = this.clusterRepo.findOne(id);
        if (cluster != null) {
            final List<Command> cmds = new ArrayList<>();
            for (final Command detached : commands) {
                final Command cmd = this.commandRepo.findOne(detached.getId());
                if (cmd != null) {
                    cmds.add(cmd);
                } else {
                    throw new GenieException(
                            HttpURLConnection.HTTP_NOT_FOUND,
                            "No command with id " + detached.getId() + " exists.");
                }
            }
            cluster.setCommands(cmds);
            return cluster.getCommands();
        } else {
            throw new GenieException(
                    HttpURLConnection.HTTP_NOT_FOUND,
                    "No cluster with id " + id + " exists.");
        }
    }