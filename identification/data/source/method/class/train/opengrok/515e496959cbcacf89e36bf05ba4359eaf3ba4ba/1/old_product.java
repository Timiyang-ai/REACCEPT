@SuppressWarnings("boxing")
    public static void dumpConfiguration(Appendable out) throws IOException,
        HistoryException
    {
        out.append("<table border=\"1\" width=\"100%\">");
        out.append("<tr><th>Variable</th><th>Value</th></tr>");
        RuntimeEnvironment env = RuntimeEnvironment.getInstance();
        printTableRow(out, "Source root", env.getSourceRootPath());
        printTableRow(out, "Data root", env.getDataRootPath());
        printTableRow(out, "CTags", env.getCtags());
        printTableRow(out, "Bug page", env.getBugPage());
        printTableRow(out, "Bug pattern", env.getBugPattern());
        printTableRow(out, "User page", env.getUserPage());
        printTableRow(out, "Review page", env.getReviewPage());
        printTableRow(out, "Review pattern", env.getReviewPattern());
        printTableRow(out, "Using projects", env.hasProjects());
        out.append("<tr><td>Ignored files</td><td>");
        printUnorderedList(out, env.getIgnoredNames().getItems());
        out.append("</td></tr>");
        printTableRow(out, "Index word limit", env.getIndexWordLimit());
        printTableRow(out, "Allow leading wildcard in search",
            env.isAllowLeadingWildcard());
        printTableRow(out, "History cache", HistoryGuru.getInstance()
            .getCacheInfo());
        out.append("</table>");
    }