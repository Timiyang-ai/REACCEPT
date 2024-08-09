public void strip() {
        int idx = message.indexOf("\n");
        if (idx != -1) {
            message.setLength(idx);
        }
        files.clear();
    }