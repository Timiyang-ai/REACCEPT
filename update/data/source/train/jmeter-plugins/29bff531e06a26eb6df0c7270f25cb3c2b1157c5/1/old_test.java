@Test
   public void testPaintRow() {
      System.out.println("paintRow");
      Graphics2DEmul g2d = new Graphics2DEmul();
      Color color = Color.red;
      double zoomFactor = 1.0;
      int limitPointFactor = 1;
      AbstractRowPlotter instance = new AbstractRowPlotterImpl();
      instance.setBoundsValues(g2d.getBounds(), minXVal, maxXVal, minYVal, maxYVal);
      instance.paintRow(g2d, testRow, color, zoomFactor, limitPointFactor);
      String expResult = "setColor: java.awt.Color[r=255,g=0,b=0]|fillOval: (7;607) - w:6 h:6|drawLine: (10;610) - (410;378)|fillOval: (407;375) - w:6 h:6|drawLine: (410;378) - (810;10)|fillOval: (807;7) - w:6 h:6|";
      String result = g2d.getResult();
      assertEquals(expResult, result);
   }