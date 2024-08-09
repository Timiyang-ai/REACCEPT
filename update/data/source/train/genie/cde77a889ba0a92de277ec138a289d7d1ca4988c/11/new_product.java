@Override
    @Transactional(
            rollbackFor = {
                    GenieException.class,
                    ConstraintViolationException.class
            }
    )
    public Job killJob(
            @NotBlank(message = "No id entered unable to kill job.")
            final String id
    ) throws GenieException {
        if (LOG.isDebugEnabled()) {
            LOG.debug("called for id: " + id);
        }
        final Job job = this.jobRepo.findOne(id);

        // do some basic error handling
        if (job == null) {
            throw new GenieNotFoundException("No job exists for id " + id + ". Unable to kill.");
        }

        // check if it is done already
        if (job.getStatus() == JobStatus.SUCCEEDED
                || job.getStatus() == JobStatus.KILLED
                || job.getStatus() == JobStatus.FAILED) {
            // job already exited, return status to user
            return job;
        } else if (job.getStatus() == JobStatus.INIT
                || job.getProcessHandle() == -1) {
            // can't kill a job if it is still initializing
            throw new GeniePreconditionException("Unable to kill job as it is still initializing");
        }

        // if we get here, job is still running - and can be killed
        // redirect to the right node if killURI points to a different node
        final String killURI = job.getKillURI();
        if (StringUtils.isBlank(killURI)) {
            throw new GeniePreconditionException("Failed to get killURI for jobID: " + id);
        }
        final String localURI = getEndPoint() + "/" + this.jobResourcePrefix + "/" + id;

        if (!killURI.equals(localURI)) {
            if (LOG.isDebugEnabled()) {
                LOG.debug("forwarding kill request to: " + killURI);
            }
            return forwardJobKill(killURI);
        }

        // if we get here, killURI == localURI, and job should be killed here
        if (LOG.isDebugEnabled()) {
            LOG.debug("killing job on same instance: " + id);
        }
        this.jobManagerFactory.getJobManager(job).kill();

        job.setJobStatus(JobStatus.KILLED, "Job killed on user request");
        job.setExitCode(ProcessStatus.JOB_KILLED.getExitCode());

        // increment counter for killed jobs
        this.stats.incrGenieKilledJobs();

        if (LOG.isDebugEnabled()) {
            LOG.debug("updating job status to KILLED for: " + id);
        }
        if (!job.isDisableLogArchival()) {
            job.setArchiveLocation(this.netUtil.getArchiveURI(id));
        }

        // all good - return results
        return job;
    }