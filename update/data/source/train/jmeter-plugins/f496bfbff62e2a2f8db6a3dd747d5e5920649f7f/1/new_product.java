public void updateGui(Sample sample)
   {
      long time = System.currentTimeMillis();
      if ((time - lastRepaint) >= REPAINT_INTERVAL)
      {
         updateGui();
         repaint();
         lastRepaint = time;
      }
   }