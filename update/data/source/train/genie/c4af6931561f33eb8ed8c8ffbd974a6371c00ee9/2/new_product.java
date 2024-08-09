Set<Application> setApplicationsForCommand(
            @NotBlank(message = "No command id entered. Unable to add applications.")
            final String id,
            @NotNull(message = "No application ids entered. Unable to set applications.")
            final Set<String> applicationIds
    ) throws GenieException;