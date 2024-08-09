void addLine(String revision, String author) {
	final Line line = new Line(revision, author);
        lines.add(line);
        widestRevision = Math.max(widestRevision, line.revision.length());
        widestAuthor = Math.max(widestAuthor, line.author.length());
    }