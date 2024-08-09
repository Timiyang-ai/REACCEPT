@Test
  public void testGetNextTimeoutIntervalMs() throws Exception {
    Runnable r = new Runnable() {
      @Override
      public void run() {
        slaveLooper.exitLoop();
        globalValue = 10;
      }
    };

    long intervalNs = 6L * 1000 * 1000 * 1000;
    slaveLooper.registerTimerEventInNanoSeconds(intervalNs, r);

    long res = 1000;

    try {
      Method method =
          slaveLooper.getClass().getSuperclass().getDeclaredMethod("getNextTimeoutIntervalMs");
      method.setAccessible(true);
      res = (Long) method.invoke(slaveLooper) * 1000 * 1000;
    } catch (NoSuchMethodException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (InvocationTargetException e) {
      e.printStackTrace();
    }

    Assert.assertNotNull(res);

    Assert.assertTrue(res <= intervalNs && res > intervalNs / 2);
  }