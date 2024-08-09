public void setServerSend() {
        if (submitEndAnnotation(Constants.SERVER_SEND)) {
            currentSpan().setCurrentSpan(null);
        }
    }