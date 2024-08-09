Set<Application> setApplicationsForCommand(
            @NotBlank(message = "No command id entered. Unable to add applications.")
            final String id,
            @NotNull(message = "No applications entered. Unable to set applications.")
            final Set<Application> applications
    ) throws GenieException;