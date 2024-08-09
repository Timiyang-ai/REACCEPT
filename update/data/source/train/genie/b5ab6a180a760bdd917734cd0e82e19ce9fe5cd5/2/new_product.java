@Override
    public void addDependenciesForCommand(
        @NotBlank(message = "No command id entered. Unable to add dependencies.") final String id,
        @NotEmpty(message = "No dependencies entered. Unable to add dependencies.") final Set<String> dependencies
    ) throws GenieException {
        this.findCommand(id).getDependencies().addAll(this.createAndGetFileEntities(dependencies));
    }