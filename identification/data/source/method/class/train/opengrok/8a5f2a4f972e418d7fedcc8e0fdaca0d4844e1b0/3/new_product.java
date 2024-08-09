public static void readableLine(int num, Writer out) throws IOException {
        String snum = String.valueOf(num);
        if (num > 1) {
            out.write("\n");
        }
        out.write("<a class=\"");
        out.write((num % 10 == 0 ? "hl" : "l"));
        out.write("\" name=\"");
        out.write(snum);
        out.write("\">");
        out.write((num > 999 ? "   " : (num > 99 ? "    " : (num > 9 ? "     " : "      "))));
        out.write(snum);
        out.write(" </a>");
    }