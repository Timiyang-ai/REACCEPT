@Test
   public void testUpdateGui_Graphs()
   {
      System.out.println("updateGui");
      instance.setSelectedIndex(0);
      instance.updateGui();
      instance.setSelectedIndex(1);
      instance.updateGui();
      instance.setSelectedIndex(2);
      instance.updateGui();
   }