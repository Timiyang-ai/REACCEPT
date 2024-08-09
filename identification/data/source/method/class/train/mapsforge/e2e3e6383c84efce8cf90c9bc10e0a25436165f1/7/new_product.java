@Override
    public void moveCenter(double moveHorizontal, double moveVertical) {
        this.moveCenterAndZoom(moveHorizontal, moveVertical, (byte) 0, true);
    }