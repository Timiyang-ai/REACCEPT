@Override
    public String readLine() throws IOException {
        synchronized (lock) {
            if (lastWasCR) {
                chompNewline();
                lastWasCR = false;
            }
            String result = super.readLine();
            if (result != null) {
                lineNumber++;
            }
            return result;
        }
    }