public static JobReport execute(Job job) throws JobException {
        try {
            return job.call();
        } catch (Exception e) {
            throw new JobException("An exception occurred during the execution of job " + job, e);
        }
    }