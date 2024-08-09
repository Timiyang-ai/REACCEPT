public static void main(String[] args) {
        // Multithreaded map rendering
        Parameters.NUMBER_OF_THREADS = 2;

        // Square frame buffer
        Parameters.SQUARE_FRAME_BUFFER = false;

        HillsRenderConfig hillsCfg = null;
        File demFolder = getDemFolder(args);
        if (demFolder != null) {
            MemoryCachingHgtReaderTileSource tileSource = new MemoryCachingHgtReaderTileSource(demFolder, new DiffuseLightShadingAlgorithm(), AwtGraphicFactory.INSTANCE);
            tileSource.setEnableInterpolationOverlap(true);
            hillsCfg = new HillsRenderConfig(tileSource);
            hillsCfg.indexOnThread();
            args = Arrays.copyOfRange(args, 1, args.length);
        }

        List<File> mapFiles = getMapFiles(args);
        final MapView mapView = createMapView();
        final BoundingBox boundingBox = addLayers(mapView, mapFiles, hillsCfg);

        final PreferencesFacade preferencesFacade = new JavaPreferences(Preferences.userNodeForPackage(Samples.class));

        final JFrame frame = new JFrame();
        frame.setTitle("Mapsforge Samples");
        frame.add(mapView);
        frame.pack();
        frame.setSize(new Dimension(800, 600));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int result = JOptionPane.showConfirmDialog(frame, MESSAGE, TITLE, JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    mapView.getModel().save(preferencesFacade);
                    mapView.destroyAll();
                    AwtGraphicFactory.clearResourceMemoryCache();
                    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                }
            }

            @Override
            public void windowOpened(WindowEvent e) {
                final Model model = mapView.getModel();
                model.init(preferencesFacade);
                if (model.mapViewPosition.getZoomLevel() == 0 || !boundingBox.contains(model.mapViewPosition.getCenter())) {
                    byte zoomLevel = LatLongUtils.zoomForBounds(model.mapViewDimension.getDimension(), boundingBox, model.displayModel.getTileSize());
                    model.mapViewPosition.setMapPosition(new MapPosition(boundingBox.getCenterPoint(), zoomLevel));
                }
            }
        });
        frame.setVisible(true);
    }