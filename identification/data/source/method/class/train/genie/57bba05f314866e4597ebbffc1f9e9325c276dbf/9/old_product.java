public Application createApplication(final Application application)
            throws GenieException {
        if (application == null) {
            throw new GeniePreconditionException("No application passed in. Unable to validate.");
        }

        application.validate();
        final HttpRequest request = BaseGenieClient.buildRequest(
                Verb.POST,
                BASE_CONFIG_APPLICATION_REST_URL,
                null,
                application);
        return (Application) this.executeRequest(request, null, Application.class);
    }