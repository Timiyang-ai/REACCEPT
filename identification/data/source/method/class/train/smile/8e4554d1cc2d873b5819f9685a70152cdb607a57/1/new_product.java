public JComponent learn() {
        JPanel pane = new JPanel(new GridLayout(1, 2));
        double[][] data = dataset[datasetIndex].toArray();
        String[] labels = dataset[datasetIndex].names();

        long clock = System.currentTimeMillis();
        IsotonicMDS isomds = IsotonicMDS.of(data, 2);
        System.out.format("Learn Kruskal's Nonmetric MDS (k=2) from %d samples in %dms\n", data.length, System.currentTimeMillis()-clock);

        PlotCanvas plot = ScatterPlot.plot(isomds.getCoordinates(), labels);
        plot.setTitle("Kruskal's Nonmetric MDS (k = 2)");
        pane.add(plot);

        clock = System.currentTimeMillis();
        isomds = IsotonicMDS.of(data, 3);
        System.out.format("Learn Kruskal's Nonmetric MDS (k=3) from %d samples in %dms\n", data.length, System.currentTimeMillis()-clock);

        plot = ScatterPlot.plot(isomds.getCoordinates(), labels);
        plot.setTitle("Kruskal's Nonmetric MDS (k = 3)");
        pane.add(plot);

        return pane;
    }