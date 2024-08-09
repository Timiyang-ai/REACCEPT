@Test
   public void testScheduleThread()
   {
      System.out.println("scheduleThread");
      HashTree hashtree = new HashTree();
      hashtree.add(new LoopController());
      JMeterThread thread = new JMeterThread(hashtree, null, null);
      SteppingThreadGroup instance = new SteppingThreadGroup();
      instance.scheduleThread(thread);
   }