private void exportData(File file, SupportSize supportSize) throws Exception {
        // fetches the preview graph sheet
        PreviewController controller = Lookup.getDefault().lookup(PreviewController.class);
        GraphSheet graphSheet = controller.getGraphSheet();
        Graph graph = graphSheet.getGraph();

        Progress.start(progress);

        // calculates progress units count
        int max = 0;
        if (graph.showNodes()) {
            max += graph.countNodes();
        }
        if (graph.showEdges()) {
            max += graph.countUnidirectionalEdges() + graph.countBidirectionalEdges();
            if (graph.showSelfLoops()) {
                max += graph.countSelfLoops();
            }
        }
        Progress.switchToDeterminate(progress, max);

        //Rectangle
        /*float ratioWidth = size.getWidth() / graphSheet.getWidth();
        float ratioHeight = size.getHeight() / graphSheet.getHeight();
        float scale = ratioWidth < ratioHeight ? ratioWidth : ratioHeight;
        float translateX = size.getWidth() / 2f - size.getWidth() / 2f * scale;
        float translateY = size.getHeight() / 2f - size.getHeight() / 2f * scale;*/

        Rectangle size = new Rectangle(pageSize);
        //size.setBackgroundColor(new BaseColor(controller.getModel().getBackgroundColor()));
        document = new Document(size);
        //document.setMargins(margin.left, margin.right, margin.top, margin.bottom);
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));
        document.open();
        cb = writer.getDirectContent();
        cb.saveState();
        //cb.transform(AffineTransform.getTranslateInstance(translateX, translateY));
        //cb.transform(AffineTransform.getScaleInstance(10, -10));

        float graphWidth = graphSheet.getWidth();
        float graphHeight = graphSheet.getHeight();
        float centerX = (graphSheet.getBottomRightPosition().getX()-graphSheet.getTopLeftPosition().getX())/2f;
        float centerY = (graphSheet.getTopLeftPosition().getY()-graphSheet.getBottomRightPosition().getY())/2f;

        float minX = Float.POSITIVE_INFINITY;
        float maxX = Float.NEGATIVE_INFINITY;
        float minY = Float.POSITIVE_INFINITY;
        float maxY = Float.NEGATIVE_INFINITY;
        for (Node n : graph.getNodes()) {
            minX = Math.min(minX, n.getPosition().getX()-n.getRadius());
            maxX = Math.max(maxX, n.getPosition().getX()+n.getRadius());
            minY = Math.min(minY, n.getPosition().getY()-n.getRadius());
            maxY = Math.max(maxY, n.getPosition().getY()+n.getRadius());
        }
        centerX = (float)(maxX-minX)/2f;
        centerY = -(float)(maxY-minY)/2f;
        graphWidth = maxX-minX;
        graphHeight = maxY-minY;

        float ratioWidth = size.getWidth() / graphWidth;
        float ratioHeight = size.getHeight() / graphHeight;
        float scale = ratioWidth < ratioHeight ? ratioWidth : ratioHeight;
        float translateX = (size.getWidth()/2f)/scale;
        float translateY = (size.getHeight()/2f)/scale;

        cb.transform(AffineTransform.getTranslateInstance(-centerX*scale, -centerY*scale));
        cb.transform(AffineTransform.getScaleInstance(scale, scale));
        cb.transform(AffineTransform.getTranslateInstance(translateX, translateY));

        /*cb.setLineWidth(1);
        cb.setRGBColorFill(0,0,0);
        cb.circle(-10,20,3);
        cb.fillStroke();
        cb.circle(30,0,3);
        cb.fillStroke();
        cb.circle(-10,0,3);
        cb.fillStroke();
        cb.circle(30,20,3);
        cb.fillStroke();
        cb.moveTo(-10,20);
        cb.lineTo(30, 20);
        cb.setLineWidth(1);
        cb.stroke();
        cb.moveTo(30, 20);
        cb.lineTo(30, 0);
        cb.setLineWidth(1);
        cb.stroke();
        cb.moveTo(30, 0);
        cb.lineTo(-10, 0);
        cb.setLineWidth(1);
        cb.stroke();
        cb.moveTo(-10, 0);
        cb.lineTo(-10, 20);
        cb.setLineWidth(1);
        cb.stroke();*/

        
        renderGraph(graphSheet.getGraph());
        cb.restoreState();
        document.close();

        Progress.finish(progress);
    }