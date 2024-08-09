void submitJob(
        @Valid
        @NotNull(message = "No job provided. Unable to submit job for execution.")
        final JobRequest jobRequest,
        @Valid
        @NotNull(message = "No cluster provided. Unable to submit job for execution")
        final Cluster cluster,
        @Valid
        @NotNull(message = "No command provided. Unable to submit job for execution")
        final Command command,
        @NotNull(message = "No applications provided. Unable to submit job for execution")
        final List<Application> applications,
        @Min(value = 1, message = "Memory can't be less than 1 MB")
        final int memory
    ) throws GenieException;