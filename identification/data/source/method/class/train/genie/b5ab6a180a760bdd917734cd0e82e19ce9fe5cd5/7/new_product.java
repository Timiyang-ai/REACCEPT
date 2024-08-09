@Override
    public void updateDependenciesForCommand(
        @NotBlank(message = "No command id entered. Unable to update dependencies.") final String id,
        @NotNull(message = "No dependencies entered. Unable to update.") final Set<String> dependencies
    ) throws GenieException {
        this.findCommand(id).setDependencies(this.createAndGetFileEntities(dependencies));
    }