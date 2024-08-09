public JobTracker findJobTracker(JobEntryCopy jobEntryCopy)
    {
        for (int i=0;i<jobTrackers.size();i++) {
            JobTracker tracker = getJobTracker(i);
            JobEntryResult result = tracker.getJobEntryResult();
            if (result!=null) {
            	if (jobEntryCopy.getName()!=null && jobEntryCopy.getName().equals(result.getJobEntryName()) && jobEntryCopy.getNr() == result.getJobEntryNr()) {
                    return tracker; 
                }
            }
        }
        return null;
    }