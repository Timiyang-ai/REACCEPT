void setJobStatus(@NotNull final JobStatus jobStatus) {
        this.status = jobStatus;

        if (jobStatus == JobStatus.INIT) {
            this.setStarted(new Date());
        } else if (jobStatus.isFinished()) {
            this.setFinished(new Date());
        }
    }