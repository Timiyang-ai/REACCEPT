void submitJob(
            @NotNull(message = "No job provided. Unable to submit job for execution.")
            @Valid
            final JobRequest jobRequest
    ) throws GenieException;