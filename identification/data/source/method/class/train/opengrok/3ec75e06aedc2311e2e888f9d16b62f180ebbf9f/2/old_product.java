void addLine(String revision, String author) {
        lines.add(new Line(revision, author));
        widestRevision = Math.max(widestRevision, revision.length());
        widestAuthor = Math.max(widestAuthor, author.length());
    }