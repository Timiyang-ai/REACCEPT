public void setClientReceived() {
        if (submitEndAnnotation("cr")) {
            currentSpan().setCurrentSpan(null);
        }
    }