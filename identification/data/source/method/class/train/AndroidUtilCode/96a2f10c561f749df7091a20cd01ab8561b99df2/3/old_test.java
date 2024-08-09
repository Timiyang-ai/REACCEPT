    @Test
    public void htmlEncode_htmlDecode() {
        String html = "<html>" +
                "<head>" +
                "<title>我的第一个 HTML 页面</title>" +
                "</head>" +
                "<body>" +
                "<p>body 元素的内容会显示在浏览器中。</p>" +
                "<p>title 元素的内容会显示在浏览器的标题栏中。</p>" +
                "</body>" +
                "</html>";
        String encodeHtml = "&lt;html&gt;&lt;head&gt;&lt;title&gt;我的第一个 HTML 页面&lt;/title&gt;&lt;/head&gt;&lt;body&gt;&lt;p&gt;body 元素的内容会显示在浏览器中。&lt;/p&gt;&lt;p&gt;title 元素的内容会显示在浏览器的标题栏中。&lt;/p&gt;&lt;/body&gt;&lt;/html&gt;";

        assertEquals(encodeHtml, EncodeUtils.htmlEncode(html));

        assertEquals(html, EncodeUtils.htmlDecode(encodeHtml).toString());
    }