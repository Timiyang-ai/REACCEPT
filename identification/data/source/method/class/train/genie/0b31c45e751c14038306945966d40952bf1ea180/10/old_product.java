public void removeCommand(final Command command)
            throws GenieException {
        if (command == null) {
            throw new GenieException(
                    HttpURLConnection.HTTP_BAD_REQUEST,
                    "No command entered unable to remove.");
        }

        if (this.commands != null) {
            this.commands.remove(command);
        }
        if (command.getClusters() != null) {
            command.getClusters().remove(this);
        }
    }