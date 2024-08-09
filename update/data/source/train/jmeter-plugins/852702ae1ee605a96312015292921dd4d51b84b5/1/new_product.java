@Override
    protected void scheduleThread(JMeterThread thread, long tgStartTime) {

        int inUserCount = getInUserCountAsInt();
        int outUserCount = getOutUserCountAsInt();

        if(inUserCount == 0) inUserCount = getNumThreads();
        if(outUserCount == 0) outUserCount = getNumThreads();
        
        //int inUserCountBurst = Math.min(Math.max(inUserCount, getInUserCountBurstAsInt()), getNumThreads());
        int inUserCountBurst = Math.min(getInUserCountBurstAsInt(), getNumThreads());
        if (inUserCountBurst <= 0) {
            inUserCountBurst = inUserCount;
        }
        
        int rampUpBucket = thread.getThreadNum() < inUserCountBurst ? 0 :
            1 + (thread.getThreadNum() - inUserCountBurst) / inUserCount;
        int rampUpBucketThreadCount = thread.getThreadNum() < inUserCountBurst ? inUserCountBurst : inUserCount;

        int threadGroupDelay = 1000 * getThreadGroupDelayAsInt();
        long ascentPoint = tgStartTime + threadGroupDelay;
        int inUserPeriod = 1000 * getInUserPeriodAsInt();
        int additionalRampUp = 1000 * getRampUpAsInt() / rampUpBucketThreadCount;
        int flightTime = 1000 * getFlightTimeAsInt();
        int outUserPeriod = 1000 * getOutUserPeriodAsInt();
        
        long rampUpDuration = 1000 * getRampUpAsInt();
        long iterationDuration = inUserPeriod + rampUpDuration;
        //number of complete iteration, ie full (in user time + rampup duration) used
        int iterationCountTotal = getNumThreads() < inUserCountBurst ? 1 :
            (int) Math.ceil((double) (getNumThreads() - inUserCountBurst) / inUserCount);

        int lastIterationUserCount = (getNumThreads() - inUserCountBurst) % inUserCount;
        if(lastIterationUserCount == 0) lastIterationUserCount = inUserCount;
        long descentPoint = ascentPoint + iterationCountTotal * iterationDuration + (1000 * getRampUpAsInt() / inUserCount) * lastIterationUserCount + flightTime;
        
        long rampUpBucketStartTime = ascentPoint + rampUpBucket * iterationDuration;
        int rampUpBucketThreadPosition = thread.getThreadNum() < inUserCountBurst ? thread.getThreadNum() :
            (thread.getThreadNum() - inUserCountBurst) % inUserCount;
        
        long startTime = rampUpBucketStartTime + rampUpBucketThreadPosition * additionalRampUp;
        long endTime = descentPoint + outUserPeriod * (int) Math.floor((double) thread.getThreadNum() / outUserCount);

        log.debug(String.format("threadNum=%d, rampUpBucket=%d, rampUpBucketThreadCount=%d, rampUpBucketStartTime=%d, rampUpBucketThreadPosition=%d, rampUpDuration=%d, iterationDuration=%d, iterationCountTotal=%d, ascentPoint=%d, descentPoint=%d, startTime=%d, endTime=%d",
               thread.getThreadNum(), rampUpBucket, rampUpBucketThreadCount, rampUpBucketStartTime, rampUpBucketThreadPosition, rampUpDuration, iterationDuration, iterationCountTotal, ascentPoint, descentPoint, startTime, endTime));

        thread.setStartTime(startTime);
        thread.setEndTime(endTime);
        thread.setScheduled(true);
    }