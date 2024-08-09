public void updateGui(Sample sample)
   {
      long time = System.currentTimeMillis();
      if ((time - lastRepaint) >= delay)
      {
         updateGui();
         repaint();
         lastRepaint = time;
      }
   }