JobStatus getJobStatus(
        @NotBlank(message = "Job id is missing and is required") String id
    ) throws GenieNotFoundException;