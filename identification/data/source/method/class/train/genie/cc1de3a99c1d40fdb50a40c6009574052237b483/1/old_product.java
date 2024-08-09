public void setJobStatus(final JobStatus jobStatus) {
        this.status = jobStatus;

        if (jobStatus == JobStatus.INIT) {
            setStartTime(System.currentTimeMillis());
        } else if (jobStatus == JobStatus.SUCCEEDED
                || jobStatus == JobStatus.KILLED
                || jobStatus == JobStatus.FAILED) {
            setFinishTime(System.currentTimeMillis());
        }
    }