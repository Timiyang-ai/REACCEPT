public void setServerSend() {
        if (submitEndAnnotation("ss")) {
            currentSpan().setCurrentSpan(ServerSpan.EMPTY);
        }
    }