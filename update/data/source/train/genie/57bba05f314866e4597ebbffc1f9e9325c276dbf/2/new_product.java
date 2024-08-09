Job createJob(
            @NotNull(message = "No job entered. Unable to create.")
            @Valid
            final Job job
    ) throws GenieException;