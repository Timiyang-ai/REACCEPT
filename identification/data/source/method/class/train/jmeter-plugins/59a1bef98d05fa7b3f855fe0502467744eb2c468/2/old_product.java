public void scheduleThread(JMeterThread thread)
   {
      int additionalRampUp = 0;
      String rampupProperty = JMeterUtils.getProperty("steppingthreadgroup.additionalrampup");
      if (rampupProperty != null)
      {
         additionalRampUp = Integer.parseInt(rampupProperty);
      }

      int threadGroupDelay = getThreadGroupDelay();

      int inUserPeriod = getInUserPeriod();
      int inUserCount = getInUserCount();

      int flightTime = getFlightTime();

      int outUserPeriod = getOutUserPeriod();
      int outUserCount = getOutUserCount();

      long ascentPoint = System.currentTimeMillis() + 1000 * threadGroupDelay;
      long descentPoint = ascentPoint + 1000 * inUserPeriod * (int) Math.floor((double) getNumThreads() / inUserCount) + 1000 * flightTime;

      thread.setStartTime(ascentPoint + 1000 * inUserPeriod * (int) Math.floor((double) thread.getThreadNum() / inUserCount) + additionalRampUp * thread.getThreadNum());
      thread.setEndTime(descentPoint + 1000 * outUserPeriod * (int) Math.floor((double) thread.getThreadNum() / outUserCount));
      thread.setScheduled(true);
   }