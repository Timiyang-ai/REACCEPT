public void startThreads()
    {
        // Now start all the threads...
        for (int i=0;i<steps.size();i++)
        {
            final StepMetaDataCombi sid = (StepMetaDataCombi)steps.get(i);
            sid.step.markStart();
            sid.step.start();
        }

        log.logDetailed(toString(), Messages.getString("Trans.Log.TransformationHasAllocated",String.valueOf(steps.size()),String.valueOf(rowsets.size()))); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    }