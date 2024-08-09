    @Test
    public void getMatches() {
        // 贪婪
        System.out.println(RegexUtils.getMatches("b.*j", "blankj blankj"));
        // 懒惰
        System.out.println(RegexUtils.getMatches("b.*?j", "blankj blankj"));
    }