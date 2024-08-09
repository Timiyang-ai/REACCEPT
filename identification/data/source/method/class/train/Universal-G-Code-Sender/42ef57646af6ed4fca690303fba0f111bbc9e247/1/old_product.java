void setLineTerminator(String terminator) {
        if (terminator.length() < 1) {
            this.lineTerminator = DEFAULT_TERMINATOR;
        } else {
            this.lineTerminator = terminator;
        }
    }