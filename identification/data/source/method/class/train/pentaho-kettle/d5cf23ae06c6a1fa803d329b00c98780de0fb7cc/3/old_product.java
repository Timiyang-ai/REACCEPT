public JobTracker findJobTracker(JobEntryCopy jobEntryCopy)
    {
        for (int i=0;i<jobTrackers.size();i++)
        {
            JobTracker tracker = getJobTracker(i);
            JobEntryResult result = tracker.getJobEntryResult();
            if (result!=null)
            {
                JobEntryCopy jobEntry = result.getJobEntry();
                if (jobEntry!=null)
                {
                    if (jobEntry.equals(jobEntryCopy)) return tracker; 
                }
            }
        }
        return null;
    }