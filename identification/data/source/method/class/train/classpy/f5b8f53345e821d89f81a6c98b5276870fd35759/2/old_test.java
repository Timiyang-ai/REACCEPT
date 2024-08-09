    @Test
    public void cutAndAppendEllipsis() {
        assertEquals("aaaaa", StringHelper.cutAndAppendEllipsis("aaaaa", 5));
        assertEquals("aa...", StringHelper.cutAndAppendEllipsis("aaaaaa", 5));
        assertEquals("aa...", StringHelper.cutAndAppendEllipsis("aa\ud801\udc00aa", 5));
        assertEquals("a...", StringHelper.cutAndAppendEllipsis("a\ud801\udc00aaa", 5));
        
        //assertEquals("...", StringHelper.cutAndAppendEllipsis("\naaa", 5));
        //assertEquals("a...", StringHelper.cutAndAppendEllipsis("a\naaa", 5));
    }