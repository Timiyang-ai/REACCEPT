public void trace(final Marker marker, final Object message, final Throwable t) {
        if (isEnabled(Level.TRACE, marker, message, t)) {
            log(marker, FQCN, Level.TRACE, messageFactory.newMessage(message), t);
        }
    }