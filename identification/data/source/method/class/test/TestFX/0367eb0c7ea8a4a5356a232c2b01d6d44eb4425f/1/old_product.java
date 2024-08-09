public Color getCapturePixelColor(Point2D location) {
        int fxRobotColor = fxRobot.getPixelColor((int) location.getX(), (int) location.getY());
        return convertFromFxRobotColor(fxRobotColor);
    }