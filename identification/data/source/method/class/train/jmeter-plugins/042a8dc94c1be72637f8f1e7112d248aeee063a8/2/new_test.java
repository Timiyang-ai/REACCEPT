@Test
   public void testScheduleThread()
   {
      System.out.println("scheduleThread");
      HashTree hashtree = new HashTree();
      hashtree.add(new LoopController());
      JMeterThread thread = new JMeterThread(hashtree, null, null);

      CollectionProperty prop = JMeterPluginsUtils.tableModelRowsToCollectionProperty(dataModel, UltimateThreadGroup.DATA_PROPERTY);
      instance.setData(prop);
      instance.testStarted();

      instance.scheduleThread(thread);

      assertTrue(thread.getStartTime() > 0);
      assertTrue(thread.getEndTime() > thread.getStartTime());
   }