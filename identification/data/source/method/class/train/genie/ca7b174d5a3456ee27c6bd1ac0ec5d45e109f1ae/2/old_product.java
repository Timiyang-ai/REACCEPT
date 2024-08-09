public void setCommands(final List<CommandEntity> commands) {
        //Clear references to this cluster in existing commands
        if (this.commands != null) {
            this.commands
                    .stream()
                    .filter(command -> command.getClusters() != null)
                    .forEach(command -> command.getClusters().remove(this));
        }
        //set the commands for this command
        this.commands = commands;

        //Add the reference in the new commands
        if (this.commands != null) {
            for (final CommandEntity command : this.commands) {
                Set<ClusterEntity> clusterEntities = command.getClusters();
                if (clusterEntities == null) {
                    clusterEntities = new HashSet<>();
                    command.setClusters(clusterEntities);
                }
                if (!clusterEntities.contains(this)) {
                    clusterEntities.add(this);
                }
            }
        }
    }