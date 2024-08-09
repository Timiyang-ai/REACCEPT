public void setClientReceived() {
        if (submitEndAnnotation(Constants.CLIENT_RECV)) {
            currentSpan().setCurrentSpan(null);
        }
    }