void addLine(String revision, String author, boolean enabled) {
        final Line line = new Line(revision, author, enabled);
        lines.add(line);
        widestRevision = Math.max(widestRevision, line.revision.length());
        widestAuthor = Math.max(widestAuthor, line.author.length());
    }