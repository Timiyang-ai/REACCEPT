public JobReport execute(Job job) {
        try {
            return executorService.submit(job).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Unable to execute job " + job.getName());
        }
    }