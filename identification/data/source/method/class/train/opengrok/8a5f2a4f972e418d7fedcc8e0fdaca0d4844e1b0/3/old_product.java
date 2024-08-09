public static void readableLine(int num, Writer out) throws IOException {
/*		out.write("\n<a name=\"");
                out.write(num);
                out.write("\">");
                out.write(num);
                out.write("\t</a>");*/
        String snum = String.valueOf(num);
        out.write("\n<a class=\"");
        out.write((num % 10 == 0 ? "hl" : "l"));
        out.write("\" name=\"");
        out.write(snum);
        out.write("\">");
        out.write((num > 999 ? "   " : (num > 99 ? "    " : (num > 9 ? "     " : "      "))));
        out.write(snum);
        out.write(" </a>");
    }