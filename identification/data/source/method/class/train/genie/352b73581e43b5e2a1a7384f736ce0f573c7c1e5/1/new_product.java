public void setJobStatus(final JobStatus jobStatus) {
        this.status = jobStatus;

        if (jobStatus == JobStatus.INIT) {
            this.setStarted(new Date());
        } else if (jobStatus != JobStatus.RUNNING) {
            setFinished(new Date());
        }
    }