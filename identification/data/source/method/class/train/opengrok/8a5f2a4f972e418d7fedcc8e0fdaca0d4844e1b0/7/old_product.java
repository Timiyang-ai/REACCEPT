public static void readableLine(int num, Writer out, Annotation annotation)
            throws IOException {
        String snum = String.valueOf(num);
        if (num > 1) {
            out.write("\n");
        }
        out.write(anchorClassStart);
        out.write((num % 10 == 0 ? "hl" : "l"));
        out.write("\" name=\"");
        out.write(snum);
        out.write(closeQuotedTag);
        out.write((num > 999 ? "   " : (num > 99 ? "    " : (num > 9 ? "     " : "      "))));
        out.write(snum);
        out.write(" ");
        out.write(anchorEnd);
        if (annotation != null) {
            String r = annotation.getRevision(num);
            boolean enabled = annotation.isEnabled(num);

            out.write("<span class=\"blame\"><span class=\"l\"> ");
            for (int i = r.length(); i < annotation.getWidestRevision(); i++) {
                out.write(" ");
            }

            if (enabled) {
                out.write(anchorLinkStart);
                out.write(URIEncode(annotation.getFilename()));
                out.write("?a=true&r=");
                out.write(URIEncode(r));
                out.write(closeQuotedTag);
            }

            htmlize(r, out);

            if (enabled) {
                out.write(anchorEnd);
            }

            out.write(" </span>");

            String a = annotation.getAuthor(num);
            out.write("<span class=\"l\"> ");
            for (int i = a.length(); i < annotation.getWidestAuthor(); i++) {
                out.write(" ");
            }
            String link = RuntimeEnvironment.getInstance().getUserPage();
            if (link != null && link.length() > 0) {
                out.write(anchorLinkStart);
                out.write(link);
                out.write(URIEncode(a));
                out.write(closeQuotedTag);
                htmlize(a, out);
                out.write(anchorEnd);
            } else {
                htmlize(a, out);
            }
            out.write(" </span></span>");
        }
    }