public CompletableFuture<Color> getCapturePixelColor(Point2D location) {
        CompletableFuture<Color> captureColorFutureResult = new CompletableFuture<>();
        Platform.runLater(() -> {
            WritableImage snapshot = scene.snapshot(null);
            captureColorFutureResult.complete(snapshot.getPixelReader().getColor(
                    (int) location.getX(), (int) location.getY()));
        });
        return captureColorFutureResult;
    }