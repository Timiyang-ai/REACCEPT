@Test
    public void testScheduleThread() {
        System.out.println("scheduleThread");
        HashTree hashtree = new HashTree();
        hashtree.add(new LoopController());
        JMeterThread thread = new JMeterThread(hashtree, null, null);
        SteppingThreadGroup instance = new SteppingThreadGroup();
        instance.setNumThreads(10);
        instance.setInUserCount("5");
        instance.setInUserPeriod("30");
        instance.setRampUp("10");
        instance.setThreadGroupDelay("5");
        instance.setFlightTime("60");

        long s1 = -1, s2;
        for (int n = 0; n < 5; n++) {
            thread.setThreadNum(n);
            instance.scheduleThread(thread);
            s2 = thread.getStartTime();
            if (s1 >= 0) {
                assertEquals(2000, s2 - s1);
            }
            s1 = s2;
        }

        thread.setThreadNum(6);
        instance.scheduleThread(thread);
        s2 = thread.getStartTime();
        assertEquals(32000, s2 - s1);
        s1 = s2;

        for (int n = 7; n < 9; n++) {
            thread.setThreadNum(n);
            instance.scheduleThread(thread);
            s2 = thread.getStartTime();
            if (s1 >= 0) {
                assertEquals(2000, s2 - s1);
            }
            s1 = s2;
        }
    }