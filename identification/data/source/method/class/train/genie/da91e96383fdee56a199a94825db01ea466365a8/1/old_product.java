public void setJobStatus(final JobStatus jobStatus) {
        this.status = jobStatus;

        if (jobStatus == Types.JobStatus.INIT) {
            setStartTime(System.currentTimeMillis());
        } else if (jobStatus == Types.JobStatus.SUCCEEDED
                || jobStatus == Types.JobStatus.KILLED
                || jobStatus == Types.JobStatus.FAILED) {
            setFinishTime(System.currentTimeMillis());
        }
    }