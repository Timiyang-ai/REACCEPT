@Test
    public void group(){
        String regexPattern = "(.*?)@(.*?)";
        String email = "venusdrogon@163.com";
        assertEquals("venusdrogon", RegexUtil.group(regexPattern, email, 1));
        assertEquals("163.com", RegexUtil.group(regexPattern, email, 2));
    }