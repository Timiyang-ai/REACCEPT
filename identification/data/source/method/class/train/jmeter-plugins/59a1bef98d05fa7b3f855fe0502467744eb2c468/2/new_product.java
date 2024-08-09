@Override
    public void scheduleThread(JMeterThread thread) {
        int threadGroupDelay = 1000 * getThreadGroupDelayAsInt();
        long ascentPoint = testStartTime + threadGroupDelay;

        int inUserPeriod = 1000 * getInUserPeriodAsInt();
        int inUserCount = getInUserCountAsInt();
        int additionalRampUp = 1000 * getRampUpAsInt() / inUserCount;

        int flightTime = 1000 * getFlightTimeAsInt();

        int outUserPeriod = 1000 * getOutUserPeriodAsInt();
        int outUserCount = getOutUserCountAsInt();

        long rampUpDuration = 1000 * getRampUpAsInt();
        long iterationDuration = inUserPeriod + rampUpDuration;
        int iterationCountTotal = (int) Math.ceil((double) getNumThreads() / inUserCount);
        int iterationCountBeforeMe = (int) Math.floor((double) thread.getThreadNum() / inUserCount);

        long descentPoint = ascentPoint + iterationCountTotal * iterationDuration + flightTime;

        long startTime = ascentPoint + iterationCountBeforeMe * iterationDuration + (thread.getThreadNum() % inUserCount) * additionalRampUp;
        long endTime = descentPoint + outUserPeriod * (int) Math.floor((double) thread.getThreadNum() / outUserCount);

        log.debug(String.format("threadNum=%d, rampUpDuration=%d, iterationDuration=%d, iterationCountTotal=%d, iterationCountBeforeMe=%d, ascentPoint=%d, descentPoint=%d, startTime=%d, endTime=%d",
                thread.getThreadNum(), rampUpDuration, iterationDuration, iterationCountTotal, iterationCountBeforeMe, ascentPoint, descentPoint, startTime, endTime));

        thread.setStartTime(startTime);
        thread.setEndTime(endTime);
        thread.setScheduled(true);
    }