public String getAuthor(int line) {
        try {
            return lines.get(line-1).author;
        } catch (IndexOutOfBoundsException e) {
            return "";
        }
    }