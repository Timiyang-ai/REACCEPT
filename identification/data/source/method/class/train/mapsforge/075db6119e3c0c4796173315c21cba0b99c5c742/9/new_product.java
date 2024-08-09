public static void main(String[] args) {
        // HA frame buffer
        FrameBufferController.HA_FRAME_BUFFER = true;

        // Multithreaded map rendering
        MapWorkerPool.NUMBER_OF_THREADS = 2;

        // Map buffer size
        ReadBuffer.MAXIMUM_BUFFER_SIZE = 6500000;

        // Square frame buffer
        FrameBufferController.SQUARE_FRAME_BUFFER = false;

        Map.Entry<File, String[]> demReturn = getDemPath(args);
        HillsRenderConfig hillsCfg = null;
        if(demReturn!=null){
            hillsCfg = new HillsRenderConfig(demReturn.getKey(), AwtGraphicFactory.INSTANCE, new SimpleShadingAlgortithm());
            args = demReturn.getValue();
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
                // model.init(preferencesFacade);
                byte zoomLevel = LatLongUtils.zoomForBounds(model.mapViewDimension.getDimension(), boundingBox, model.displayModel.getTileSize());
                model.mapViewPosition.setMapPosition(new MapPosition(boundingBox.getCenterPoint(), zoomLevel));
            }
        });
        frame.setVisible(true);
    }