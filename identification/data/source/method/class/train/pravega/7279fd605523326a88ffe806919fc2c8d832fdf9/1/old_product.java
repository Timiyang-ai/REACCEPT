void flush() {
        Exceptions.checkNotClosed(this.closed.get(), this);
        this.outputStream.flush();
    }