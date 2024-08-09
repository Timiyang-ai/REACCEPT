public void setCommands(final List<Command> commands) {
        //Clear references to this cluster in existing commands
        if (this.commands != null) {
            for (final Command command : this.commands) {
                if (command.getClusters() != null) {
                    command.getClusters().remove(this);
                }
            }
        }
        //set the commands for this command
        this.commands = commands;

        //Add the reference in the new commands
        if (this.commands != null) {
            for (final Command command : this.commands) {
                Set<Cluster> clusters = command.getClusters();
                if (clusters == null) {
                    clusters = new HashSet<Cluster>();
                    command.setClusters(clusters);
                }
                if (!clusters.contains(this)) {
                    clusters.add(this);
                }
            }
        }
    }