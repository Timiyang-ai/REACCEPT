public void startThreads()
    {
        // Now start all the threads...
        for (int i=0;i<steps.size();i++)
        {
            final StepMetaDataCombi sid = (StepMetaDataCombi)steps.get(i);
            sid.step.markStart();
            sid.step.start();
        }

        log.logDetailed(toString(), "Transformation has allocated "+steps.size()+" threads and "+rowsets.size()+" rowsets.");
    }