public String getName() {
        if (name == null || name.length() == 0) {
            return getStreamName() == null || getStreamName().length() == 0 ? getFilename() : getStreamName();
        } else {
            return name;
        }
    }