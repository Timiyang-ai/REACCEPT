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
        Rectangle size = new Rectangle(pageSize);
        size.setBackgroundColor(new BaseColor(controller.getModel().getBackgroundColor()));
        float ratioWidth = size.getWidth() / graphSheet.getWidth();
        float ratioHeight = size.getHeight() / graphSheet.getHeight();
        float scale = ratioWidth < ratioHeight ? ratioWidth : ratioHeight;
        float translateX = size.getWidth() / 2f - size.getWidth() / 2f * scale;
        float translateY = size.getHeight() / 2f - size.getHeight() / 2f * scale;

        document = new Document(size);
        //document.setMargins(margin.left, margin.right, margin.top, margin.bottom);
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));
        document.open();
        cb = writer.getDirectContent();
        cb.saveState();
        cb.transform(AffineTransform.getTranslateInstance(translateX, translateY));
        cb.transform(AffineTransform.getScaleInstance(scale, -scale));

        //
        renderGraph(graphSheet.getGraph());
        cb.restoreState();
        document.close();

        Progress.finish(progress);
    }