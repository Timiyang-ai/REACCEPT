@Test
   public void testTableModelToCollectionProperty()
   {
      System.out.println("tableModelToCollectionProperty");
        PowerTableModel model = new PowerTableModel();
      CollectionProperty prop = JMeterPluginsUtils.tableModelToCollectionProperty(model, "");
      assertTrue(prop instanceof CollectionProperty);
   }