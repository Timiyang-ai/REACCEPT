@Override
    public String toString ()
    {
        StringBuilder sb = new StringBuilder(getClass().getSimpleName());
        sb.append("{");

        if (name != null) {
            sb.append(" name:").append(name);
        }

        if (interline != null) {
            sb.append(" interline:").append(interline);
        }

        if (stemNumber != null) {
            sb.append(" stem-number:").append(stemNumber);
        }

        if (withLedger != null) {
            sb.append(" with-ledger:").append(withLedger);
        }

        if (pitchPosition != null) {
            sb.append(" pitch-position:").append(pitchPosition);
        }

        sb.append("}");

        return sb.toString();
    }