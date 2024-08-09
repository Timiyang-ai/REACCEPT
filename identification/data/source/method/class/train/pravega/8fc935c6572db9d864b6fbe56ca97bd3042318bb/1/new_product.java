void reset() {
        Exceptions.checkNotClosed(this.closed, this);
        this.currentFrame = null;
        this.hasDataInCurrentFrame = false;
    }