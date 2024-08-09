public List<Future<JobReport>> submitAll(Job... jobs) {
        return submitAll(Arrays.asList(jobs));
    }