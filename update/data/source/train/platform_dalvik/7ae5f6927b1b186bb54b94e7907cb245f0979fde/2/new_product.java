@Override
    public String toString() {
        StringBuilder sb = new StringBuilder(10);

        format((getYear() + 1900), 4, sb);
        sb.append('-');
        format((getMonth() + 1), 2, sb);
        sb.append('-');
        format(getDate(), 2, sb);

        return sb.toString();
    }