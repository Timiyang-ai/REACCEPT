public void scheduleThread(JMeterThread thread) {
        log.debug("Scheduling thread: " + thread.getThreadName());
        if (threadsToSchedule < 1) {
            if (!scheduleIT.hasNext()) {
                throw new RuntimeException("Not enough schedule records for thread #" + thread.getThreadName());
            }

            currentRecord = (CollectionProperty) scheduleIT.next();
            threadsToSchedule = currentRecord.get(0).getIntValue();
        }

        int numThreads = currentRecord.get(0).getIntValue();
        int initialDelay = currentRecord.get(1).getIntValue();
        int startRampUp = currentRecord.get(2).getIntValue();
        int flightTime = currentRecord.get(3).getIntValue();
        int endRampUp = currentRecord.get(4).getIntValue();

        long ascentPoint = System.currentTimeMillis() + 1000 * initialDelay;
        final int rampUpDelayForThread = (int) Math.floor(1000 * startRampUp * (double) threadsToSchedule / numThreads);
        long startTime = ascentPoint + rampUpDelayForThread;
        long descentPoint = startTime + 1000 * flightTime + 1000 * startRampUp - rampUpDelayForThread;

        thread.setStartTime(startTime);
        thread.setEndTime(descentPoint + (int) Math.floor(1000 * endRampUp * (double) threadsToSchedule / numThreads));

        thread.setScheduled(true);
        threadsToSchedule--;
    }