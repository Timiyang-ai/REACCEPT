public JobTracker findJobTracker(JobEntryCopy jobEntryCopy)
    {
        for (int i=jobTrackers.size()-1;i>=0;i--) {
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