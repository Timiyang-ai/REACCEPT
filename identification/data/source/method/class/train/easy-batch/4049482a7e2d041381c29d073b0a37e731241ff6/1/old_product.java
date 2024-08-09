public List<Future<JobReport>> submitAll(Job... jobs) {
        List<Job> jobList = new ArrayList<>();
        Collections.addAll(jobList, jobs);
        try {
            return executorService.invokeAll(jobList);
        } catch (InterruptedException e) {
            throw new RuntimeException("Unable to execute jobs");
        }
    }