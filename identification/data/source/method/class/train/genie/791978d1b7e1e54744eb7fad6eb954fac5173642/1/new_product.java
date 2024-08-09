void setJobStatus(@NotNull final JobStatus jobStatus) {
        this.status = jobStatus;

        if (jobStatus == JobStatus.INIT) {
            this.setStarted(Instant.now());
        } else if (jobStatus.isFinished()) {
            this.setFinished(Instant.now());
        }
    }