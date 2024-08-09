    @Test
    public void parseCharset() {
        // Like the ones we usually see
        headers.put("Content-Type", "text/plain; charset=utf-8");
        assertEquals("utf-8", HttpHeaderParser.parseCharset(headers));

        // Charset specified, ignore default charset
        headers.put("Content-Type", "text/plain; charset=utf-8");
        assertEquals("utf-8", HttpHeaderParser.parseCharset(headers, "ISO-8859-1"));

        // Extra whitespace
        headers.put("Content-Type", "text/plain;    charset=utf-8 ");
        assertEquals("utf-8", HttpHeaderParser.parseCharset(headers));

        // Extra parameters
        headers.put("Content-Type", "text/plain; charset=utf-8; frozzle=bar");
        assertEquals("utf-8", HttpHeaderParser.parseCharset(headers));

        // No Content-Type header
        headers.clear();
        assertEquals("ISO-8859-1", HttpHeaderParser.parseCharset(headers));

        // No Content-Type header, use default charset
        headers.clear();
        assertEquals("utf-8", HttpHeaderParser.parseCharset(headers, "utf-8"));

        // Empty value
        headers.put("Content-Type", "text/plain; charset=");
        assertEquals("ISO-8859-1", HttpHeaderParser.parseCharset(headers));

        // None specified
        headers.put("Content-Type", "text/plain");
        assertEquals("ISO-8859-1", HttpHeaderParser.parseCharset(headers));

        // None charset specified, use default charset
        headers.put("Content-Type", "application/json");
        assertEquals("utf-8", HttpHeaderParser.parseCharset(headers, "utf-8"));

        // None specified, extra semicolon
        headers.put("Content-Type", "text/plain;");
        assertEquals("ISO-8859-1", HttpHeaderParser.parseCharset(headers));
    }