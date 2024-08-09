public Date getTimestamp() {
        // BEGIN android-changed
        // copied from a newer version of harmony
        return (Date) timestamp.clone();
        // END android-changed
    }