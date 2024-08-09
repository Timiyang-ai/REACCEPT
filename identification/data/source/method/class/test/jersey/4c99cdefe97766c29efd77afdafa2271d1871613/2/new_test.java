@Test
    public void cloneTest() throws CloneNotSupportedException {
        StringBuilder sb = new StringBuilder();

        int status = 200;
        List<String> type = Arrays.asList("text/plain", "text/html");
        List<String> encoding = Arrays.asList("gzip", "compress");
        List<String> lang = Arrays.asList("en-US", "en-GB", "zh-CN");

        String name = "name_1";
        String value = "value_1";
        Cookie ck1 = new Cookie(name, value);
        NewCookie nck1 = new NewCookie(ck1);

        List<String> cookies = Arrays.asList(nck1.toString().toLowerCase());

        Response.ResponseBuilder respb1 = Response.status(status)
                .header("Content-type", "text/plain").header("Content-type",
                "text/html").header("Content-Language", "en-US")
                .header("Content-Language", "en-GB").header("Content-Language",
                "zh-CN").header("Cache-Control", "no-transform")
                .header("Set-Cookie", "name_1=value_1;version=1");
        Response.ResponseBuilder respb2 = respb1.clone();

        Response resp2 = respb2.build();

        String tmp = verifyResponse(resp2, null, status, encoding, lang, type,
                null, null, cookies);
        if (tmp.endsWith("false")) {
            System.out.println("### " + sb.toString());
            fail();
        }
        sb.append(tmp).append(newline);

        String content = "TestOnly";
        Response resp1 = respb1.entity(content).cookie((NewCookie) null).build();
        tmp = verifyResponse(resp1, content, status, encoding, lang, type,
                null, null, null);
        if (tmp.endsWith("false")) {
            System.out.println("### " + sb.toString());
            fail();
        }

        MultivaluedMap<java.lang.String, java.lang.Object> mvp =
                resp1.getMetadata();
        if (mvp.containsKey("Set-Cookie")) {
            sb.append("Response contains unexpected Set-Cookie: ").append(mvp.getFirst("Set-Cookie").toString()).append(newline);
            System.out.println("### " + sb.toString());
            fail();
        }
        sb.append(tmp).append(newline);
    }