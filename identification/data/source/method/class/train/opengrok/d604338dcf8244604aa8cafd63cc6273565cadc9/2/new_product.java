public String getRevision(int line) {
        try {
            return lines.get(line-1).revision;
        } catch (IndexOutOfBoundsException e) {
            return "";
        }
    }