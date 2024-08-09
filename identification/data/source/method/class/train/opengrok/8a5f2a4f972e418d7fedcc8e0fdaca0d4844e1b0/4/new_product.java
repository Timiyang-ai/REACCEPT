public static void readableLine(int num, Writer out, Annotation annotation,
            String userPageLink, String userPageSuffix, String project)
            throws IOException {
        // this method should go to JFlexXref
        String snum = String.valueOf(num);
        if (num > 1) {
            out.write("\n");
        }
        out.write(anchorClassStart);
        out.write(num % 10 == 0 ? "hl" : "l");
        out.write("\" name=\"");
        out.write(snum);
        out.write("\" href=\"#");
        out.write(snum);
        out.write(closeQuotedTag);
        out.write(snum);
        out.write(anchorEnd);
        if (annotation != null) {
            String r = annotation.getRevision(num);
            boolean enabled = annotation.isEnabled(num);
            out.write("<span class=\"blame\">");
            if (enabled) {
                out.write(anchorClassStart);
                out.write("r\" href=\"");
                out.write(URIEncode(annotation.getFilename()));
                out.write("?a=true&amp;r=");
                out.write(URIEncode(r));
                String msg = annotation.getDesc(r);
                if (msg != null) {
                    out.write("\" title=\"");
                    out.write(msg);
                }
                int versionVisibility = annotation.getFileVersionsCount() > 1
                    ? 221 - ((221 * Math.max(0, annotation.getFileVersion(r)-1) ) / (annotation.getFileVersionsCount()-1) ) //keep this in range 0..221, 0 most visible
                    : 221
                ;
                out.write("\" style=\"background-color: rgb(221,221,"+  versionVisibility +");");
                out.write(closeQuotedTag);
            }
            StringBuilder buf = new StringBuilder();
            htmlize(r, buf);
            out.write(buf.toString());
            buf.setLength(0);
            if (enabled) {
                RuntimeEnvironment env = RuntimeEnvironment.getInstance();

                out.write(anchorEnd);

                // Write link to search the revision in current project.
                out.write(anchorClassStart);
                out.write("search\" href=\"" + env.getUrlPrefix());
                out.write("defs=&refs=&path=");
                out.write(project);
                out.write("&hist=" + URIEncode(r));
                out.write("&type=\" title=\"Search history for this changeset");
                out.write(closeQuotedTag);
                out.write("S");
                out.write(anchorEnd);
            }
            String a = annotation.getAuthor(num);
            if (userPageLink == null) {
                out.write("<span class=\"a\">");
                htmlize(a, buf);
                out.write(buf.toString());
                out.write("</span>");
                buf.setLength(0);
            } else {
                out.write(anchorClassStart);
                out.write("a\" href=\"");
                out.write(userPageLink);
                out.write(URIEncode(a));
                if (userPageSuffix != null) {
                    out.write(userPageSuffix);
                }
                out.write(closeQuotedTag);
                htmlize(a, buf);
                out.write(buf.toString());
                buf.setLength(0);
                out.write(anchorEnd);
            }
            out.write("</span>");
        }
    }