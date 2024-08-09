@Override
    @Transactional(readOnly = true)
    public Set<String> getDependenciesForCommand(
        @NotBlank(message = "No command id entered. Unable to get dependencies.")
        final String id
    ) throws GenieException {
        return this.findCommand(id).getDependencies();
    }