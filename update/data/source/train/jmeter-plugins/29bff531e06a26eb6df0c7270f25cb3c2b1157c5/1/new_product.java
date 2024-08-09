public synchronized void paintRow(Graphics2D g2d, AbstractGraphRow row, Color color, double zoomFactor, int limitPointFactor) {
      
      Iterator<Entry<Long, AbstractGraphPanelChartElement>> it = row.iterator();
      Entry<Long, AbstractGraphPanelChartElement> element;

      Stroke olStroke = null;

      prevX = -1;
      prevY = chartRect.y + chartRect.height;

      mustDrawFirstZeroingLine = chartSettings.isDrawFinalZeroingLines();

      olStroke = g2d.getStroke();

      if (row.isDrawThickLines()) {
         g2d.setStroke(chartSettings.getThickStroke());
      } else {
         g2d.setStroke(getLineStroke());
      }

      g2d.setColor(color);

      while (it.hasNext()) {
         if (!row.isDrawOnChart()) {
            continue;
         }
         if (limitPointFactor == 1) {

            element = it.next();
            AbstractGraphPanelChartElement elt = (AbstractGraphPanelChartElement) element.getValue();

            //not compatible with factor != 1, ie cannot be used if limit nb of point is selected.
            if (chartSettings.getHideNonRepValLimit() > 0) {
               while (!elt.isPointRepresentative(chartSettings.getHideNonRepValLimit()) && it.hasNext()) {
                  element = it.next();
                  elt = (AbstractGraphPanelChartElement) element.getValue();
               }

               if (!elt.isPointRepresentative(chartSettings.getHideNonRepValLimit())) {
                  break;
               }
            }

            calcPointX = element.getKey().doubleValue();
            calcPointY = elt.getValue();
         } else {
            calcPointX = 0;
            calcPointY = 0;
            int nbPointProcessed = 0;
            for (int i = 0; i < limitPointFactor; i++) {
               if (it.hasNext()) {
                  element = it.next();
                  calcPointX = calcPointX + element.getKey().doubleValue();
                  calcPointY = calcPointY + ((AbstractGraphPanelChartElement) element.getValue()).getValue();
                  nbPointProcessed++;
               }
            }
            calcPointX = calcPointX / (double) nbPointProcessed;
            calcPointY = calcPointY / (double) nbPointProcessed;
         }

         calcPointY = calcPointY * zoomFactor;

         x = chartRect.x + (int) ((calcPointX - minXVal) * dxForDVal);
         int yHeight = (int) ((calcPointY - minYVal) * dyForDVal);
         y = chartRect.y + chartRect.height - yHeight;

         //now x and y are set, we can call plotter
         processPoint(g2d, row.getGranulationValue());
         
         //set prevX, prevY
         if (isChartPointValid(x, y)) {
            if(allowMarkers) {
               processMarker(g2d, row);
            }
            prevX = x;
            prevY = y;
         }

         if (row.isDrawValueLabel() && isChartPointValid(x, y) && y >= chartRect.y) {
            drawLabels(g2d, row, calcPointY);
         }
      }

      processFinalLines(row, g2d);

      x=0;
      y=0;
      prevX = -1;
      prevY = chartRect.y + chartRect.height;

      postPaintRow(row, g2d);

      g2d.setStroke(olStroke);
   }