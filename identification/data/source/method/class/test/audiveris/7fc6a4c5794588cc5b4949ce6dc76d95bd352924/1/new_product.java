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

        if (pitchPosition != null) {
            sb.append(" pitch-position:").append(pitchPosition);
        }

        sb.append("}");

        return sb.toString();
    }