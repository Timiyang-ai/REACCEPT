private static String escapeHtml(String string) {
        StringWriter writer = new StringWriter();
        //TODO Try to replace usage of HtmlEscaper so that the "dot-lang" doesn't have dependency on mustache
        HtmlEscaper.escape(string, writer);
        return writer.toString();
    }