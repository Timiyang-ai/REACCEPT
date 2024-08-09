public String getCoordinates() {
        if (path == null) {
            return null;
        }
        if (path.endsWith(name)) {
            return path.substring(0, path.length()-name.length()-1);
        } else {
            return path;
        }
    }