public Color getCapturePixelColor(Point2D location) {
        if (!Platform.isFxApplicationThread()) {
            throw new RuntimeException("JavafxRobotAdapter#getCapturePixelColor(..) must be called on JavaFX " +
                    "application thread but was: " + Thread.currentThread());
        }
        WritableImage snapshot = scene.snapshot(null);
        return snapshot.getPixelReader().getColor((int) location.getX(), (int) location.getY());
    }