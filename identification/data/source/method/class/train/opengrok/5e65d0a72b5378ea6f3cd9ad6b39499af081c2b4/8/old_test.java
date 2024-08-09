    @Test
    public void breadcrumbPath() {
        assertEquals(null, Util.breadcrumbPath("/root/", null));

        assertEquals("", Util.breadcrumbPath("/root/", ""));

        assertEquals("<a href=\"/root/x\">x</a>",
                Util.breadcrumbPath("/root/", "x"));
        assertEquals("<a href=\"/root/xx\">xx</a>",
                Util.breadcrumbPath("/root/", "xx"));

        // parent directories have a trailing slash in href
        assertEquals("<a href=\"/r/a/\">a</a>/<a href=\"/r/a/b\">b</a>",
                Util.breadcrumbPath("/r/", "a/b"));
        // if basename is a dir (ends with file seperator), href link also
        // ends with a '/'
        assertEquals("<a href=\"/r/a/\">a</a>/<a href=\"/r/a/b/\">b</a>/",
                Util.breadcrumbPath("/r/", "a/b/"));
        // should work the same way with a '.' as file separator
        assertEquals("<a href=\"/r/java/\">java</a>."
                + "<a href=\"/r/java/lang/\">lang</a>."
                + "<a href=\"/r/java/lang/String\">String</a>",
                Util.breadcrumbPath("/r/", "java.lang.String", '.'));
        // suffix added to the link?
        assertEquals("<a href=\"/root/xx&project=y\">xx</a>",
                Util.breadcrumbPath("/root/", "xx", '/', "&project=y", false));
        // compact: path needs to be resolved to /xx and no link is added
        // for the virtual root directory (parent) but emitted as plain text.
        // Prefix gets just prefixed as is and not mangled wrt. path -> "//"
        assertEquals("/<a href=\"/root//xx&project=y\">xx</a>",
                Util.breadcrumbPath("/root/", "../xx", '/', "&project=y", true));
        // relative pathes are resolved wrt. / , so path resolves to /a/c/d 
        assertEquals("/<a href=\"/r//a/\">a</a>/"
                + "<a href=\"/r//a/c/\">c</a>/"
                + "<a href=\"/r//a/c/d\">d</a>",
                Util.breadcrumbPath("/r/", "../a/b/../c//d", '/', "", true));
    }