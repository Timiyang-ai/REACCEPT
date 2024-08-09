public void addCommand(final Command command)
            throws GenieException {
        if (command == null) {
            throw new GenieException(
                    HttpURLConnection.HTTP_BAD_REQUEST,
                    "No command entered unable to add.");
        }

        if (this.commands == null) {
            this.commands = new ArrayList<>();
        }
        this.commands.add(command);

        Set<Cluster> clusters = command.getClusters();
        if (clusters == null) {
            clusters = new HashSet<>();
            command.setClusters(clusters);
        }
        clusters.add(this);
    }