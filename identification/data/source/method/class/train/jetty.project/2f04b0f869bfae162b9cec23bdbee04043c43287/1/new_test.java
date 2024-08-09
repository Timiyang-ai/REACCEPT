@Test
    public void testAddDecodedPaths()
    {
        assertEquals("null+null", URIUtil.addPaths(null,null),null);
        assertEquals("null+", URIUtil.addPaths(null,""),"");
        assertEquals("null+bbb", URIUtil.addPaths(null,"bbb"),"bbb");
        assertEquals("null+/", URIUtil.addPaths(null,"/"),"/");
        assertEquals("null+/bbb", URIUtil.addPaths(null,"/bbb"),"/bbb");

        assertEquals("+null", URIUtil.addPaths("",null),"");
        assertEquals("+", URIUtil.addPaths("",""),"");
        assertEquals("+bbb", URIUtil.addPaths("","bbb"),"bbb");
        assertEquals("+/", URIUtil.addPaths("","/"),"/");
        assertEquals("+/bbb", URIUtil.addPaths("","/bbb"),"/bbb");

        assertEquals("aaa+null", URIUtil.addPaths("aaa",null),"aaa");
        assertEquals("aaa+", URIUtil.addPaths("aaa",""),"aaa");
        assertEquals("aaa+bbb", URIUtil.addPaths("aaa","bbb"),"aaa/bbb");
        assertEquals("aaa+/", URIUtil.addPaths("aaa","/"),"aaa/");
        assertEquals("aaa+/bbb", URIUtil.addPaths("aaa","/bbb"),"aaa/bbb");

        assertEquals("/+null", URIUtil.addPaths("/",null),"/");
        assertEquals("/+", URIUtil.addPaths("/",""),"/");
        assertEquals("/+bbb", URIUtil.addPaths("/","bbb"),"/bbb");
        assertEquals("/+/", URIUtil.addPaths("/","/"),"/");
        assertEquals("/+/bbb", URIUtil.addPaths("/","/bbb"),"/bbb");

        assertEquals("aaa/+null", URIUtil.addPaths("aaa/",null),"aaa/");
        assertEquals("aaa/+", URIUtil.addPaths("aaa/",""),"aaa/");
        assertEquals("aaa/+bbb", URIUtil.addPaths("aaa/","bbb"),"aaa/bbb");
        assertEquals("aaa/+/", URIUtil.addPaths("aaa/","/"),"aaa/");
        assertEquals("aaa/+/bbb", URIUtil.addPaths("aaa/","/bbb"),"aaa/bbb");

        assertEquals(";JS+null", URIUtil.addPaths(";JS",null),";JS");
        assertEquals(";JS+", URIUtil.addPaths(";JS",""),";JS");
        assertEquals(";JS+bbb", URIUtil.addPaths(";JS","bbb"),";JS/bbb");
        assertEquals(";JS+/", URIUtil.addPaths(";JS","/"),";JS/");
        assertEquals(";JS+/bbb", URIUtil.addPaths(";JS","/bbb"),";JS/bbb");

        assertEquals("aaa;JS+null", URIUtil.addPaths("aaa;JS",null),"aaa;JS");
        assertEquals("aaa;JS+", URIUtil.addPaths("aaa;JS",""),"aaa;JS");
        assertEquals("aaa;JS+bbb", URIUtil.addPaths("aaa;JS","bbb"),"aaa;JS/bbb");
        assertEquals("aaa;JS+/", URIUtil.addPaths("aaa;JS","/"),"aaa;JS/");
        assertEquals("aaa;JS+/bbb", URIUtil.addPaths("aaa;JS","/bbb"),"aaa;JS/bbb");

        assertEquals("aaa;JS+null", URIUtil.addPaths("aaa/;JS",null),"aaa/;JS");
        assertEquals("aaa;JS+", URIUtil.addPaths("aaa/;JS",""),"aaa/;JS");
        assertEquals("aaa;JS+bbb", URIUtil.addPaths("aaa/;JS","bbb"),"aaa/;JS/bbb");
        assertEquals("aaa;JS+/", URIUtil.addPaths("aaa/;JS","/"),"aaa/;JS/");
        assertEquals("aaa;JS+/bbb", URIUtil.addPaths("aaa/;JS","/bbb"),"aaa/;JS/bbb");

        assertEquals("?A=1+null", URIUtil.addPaths("?A=1",null),"?A=1");
        assertEquals("?A=1+", URIUtil.addPaths("?A=1",""),"?A=1");
        assertEquals("?A=1+bbb", URIUtil.addPaths("?A=1","bbb"),"?A=1/bbb");
        assertEquals("?A=1+/", URIUtil.addPaths("?A=1","/"),"?A=1/");
        assertEquals("?A=1+/bbb", URIUtil.addPaths("?A=1","/bbb"),"?A=1/bbb");

        assertEquals("aaa?A=1+null", URIUtil.addPaths("aaa?A=1",null),"aaa?A=1");
        assertEquals("aaa?A=1+", URIUtil.addPaths("aaa?A=1",""),"aaa?A=1");
        assertEquals("aaa?A=1+bbb", URIUtil.addPaths("aaa?A=1","bbb"),"aaa?A=1/bbb");
        assertEquals("aaa?A=1+/", URIUtil.addPaths("aaa?A=1","/"),"aaa?A=1/");
        assertEquals("aaa?A=1+/bbb", URIUtil.addPaths("aaa?A=1","/bbb"),"aaa?A=1/bbb");

        assertEquals("aaa?A=1+null", URIUtil.addPaths("aaa/?A=1",null),"aaa/?A=1");
        assertEquals("aaa?A=1+", URIUtil.addPaths("aaa/?A=1",""),"aaa/?A=1");
        assertEquals("aaa?A=1+bbb", URIUtil.addPaths("aaa/?A=1","bbb"),"aaa/?A=1/bbb");
        assertEquals("aaa?A=1+/", URIUtil.addPaths("aaa/?A=1","/"),"aaa/?A=1/");
        assertEquals("aaa?A=1+/bbb", URIUtil.addPaths("aaa/?A=1","/bbb"),"aaa/?A=1/bbb");

    }