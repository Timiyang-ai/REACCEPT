@Override
    @Transactional
    public int markZombies() {
        if (LOG.isDebugEnabled()) {
            LOG.debug("called");
        }
        final ProcessStatus zombie = ProcessStatus.ZOMBIE_JOB;
        final long currentTime = new Date().getTime();

        @SuppressWarnings("unchecked")
        final List<Job> jobs = this.jobRepo.findAll(JobSpecs.findZombies(currentTime, this.zombieTime));
        for (final Job job : jobs) {
            job.setStatus(JobStatus.FAILED);
            job.setFinished(new Date());
            job.setExitCode(zombie.getExitCode());
            job.setStatusMsg(zombie.getMessage());
        }
        return jobs.size();
    }