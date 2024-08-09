public void scheduleThread(JMeterThread thread)
   {
      ThreadScheduleParams params = new ThreadScheduleParams(thread, getData());

      long ascentPoint = System.currentTimeMillis() + 1000 * params.initialDelay;
      final int rampUpDelayForThread = (int) Math.floor(1000 * params.startRampUp * (double) params.threadSeqNum / params.numThreads);
      long startTime = ascentPoint + rampUpDelayForThread;
      long descentPoint = startTime + 1000 * params.flightTime + 1000 * params.startRampUp - rampUpDelayForThread;

      thread.setStartTime(startTime);
      thread.setEndTime(descentPoint+(int) Math.floor(1000 * params.endRampUp * (double) params.threadSeqNum / params.numThreads));

      thread.setScheduled(true);
   }