public Command createCommand(final Command command)
            throws GenieException {
        if (command == null) {
            throw new GeniePreconditionException("No command entered to validate");
        }

        final HttpRequest request = BaseGenieClient.buildRequest(
                Verb.POST,
                BASE_CONFIG_COMMAND_REST_URL,
                null,
                command);
        return (Command) this.executeRequest(request, null, Command.class);
    }