public void trace(Marker marker, Object message, Throwable t) {
        if (isEnabled(Level.TRACE, marker, message, t)) {
            log(marker, FQCN, Level.TRACE, messageFactory.newMessage(message), t);
        }
    }