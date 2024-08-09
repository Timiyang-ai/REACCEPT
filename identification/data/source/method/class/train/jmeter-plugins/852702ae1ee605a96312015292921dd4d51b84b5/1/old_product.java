@Override
    protected void scheduleThread(JMeterThread thread, long tgStartTime) {

        int inUserCount = getInUserCountAsInt();
        int outUserCount = getOutUserCountAsInt();

        if(inUserCount == 0) inUserCount = getNumThreads();
        if(outUserCount == 0) outUserCount = getNumThreads();

        int threadGroupDelay = 1000 * getThreadGroupDelayAsInt();
        long ascentPoint = tgStartTime + threadGroupDelay;
        int inUserPeriod = 1000 * getInUserPeriodAsInt();
        int additionalRampUp = 1000 * getRampUpAsInt() / inUserCount;
        int flightTime = 1000 * getFlightTimeAsInt();
        int outUserPeriod = 1000 * getOutUserPeriodAsInt();
        
        long rampUpDuration = 1000 * getRampUpAsInt();
        long iterationDuration = inUserPeriod + rampUpDuration;
        //number of complete iteration, ie full (in user time + rampup duration) used
        int iterationCountTotal = (int) Math.ceil((double) getNumThreads() / inUserCount) - 1;
        int iterationCountBeforeMe = (int) Math.floor((double) thread.getThreadNum() / inUserCount);

        int lastIterationUserCount = getNumThreads() % inUserCount;
        if(lastIterationUserCount == 0) lastIterationUserCount = inUserCount;
        long descentPoint = ascentPoint + iterationCountTotal * iterationDuration + additionalRampUp * lastIterationUserCount + flightTime;

        long startTime = ascentPoint + iterationCountBeforeMe * iterationDuration + (thread.getThreadNum() % inUserCount) * additionalRampUp;
        long endTime = descentPoint + outUserPeriod * (int) Math.floor((double) thread.getThreadNum() / outUserCount);

        log.debug(String.format("threadNum=%d, rampUpDuration=%d, iterationDuration=%d, iterationCountTotal=%d, iterationCountBeforeMe=%d, ascentPoint=%d, descentPoint=%d, startTime=%d, endTime=%d",
                thread.getThreadNum(), rampUpDuration, iterationDuration, iterationCountTotal, iterationCountBeforeMe, ascentPoint, descentPoint, startTime, endTime));

        thread.setStartTime(startTime);
        thread.setEndTime(endTime);
        thread.setScheduled(true);
    }