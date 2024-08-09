@Override
    @Transactional(readOnly = true)
    public Job getJob(
            @NotBlank(message = "No id entered. Unable to get job.")
            final String id
    ) throws GenieException {
        if (LOG.isDebugEnabled()) {
            LOG.debug("called for id: " + id);
        }

        final Job job = this.jobRepo.findOne(id);
        if (job != null) {
            return job;
        } else {
            throw new GenieNotFoundException(
                    "No job exists for id " + id + ". Unable to retrieve.");
        }
    }