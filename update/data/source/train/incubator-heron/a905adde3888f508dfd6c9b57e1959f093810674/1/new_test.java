@Test
  public void testGetNextTimeoutIntervalMs() throws Exception {
    Runnable r = new Runnable() {
      @Override
      public void run() {
        slaveLooper.exitLoop();
        globalValue = 10;
      }
    };

    Duration interval = Duration.ofSeconds(6);
    slaveLooper.registerTimerEvent(interval, r);

    Duration res = Duration.ofNanos(1000);

    try {
      Method method =
          slaveLooper.getClass().getSuperclass().getDeclaredMethod("getNextTimeoutInterval");
      method.setAccessible(true);
      res = (Duration) method.invoke(slaveLooper);
    } catch (NoSuchMethodException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (InvocationTargetException e) {
      e.printStackTrace();
    }

    Assert.assertNotNull(res);

    Assert.assertTrue(res.compareTo(interval) <= 0 && res.compareTo(interval.dividedBy(2)) > 0);
  }