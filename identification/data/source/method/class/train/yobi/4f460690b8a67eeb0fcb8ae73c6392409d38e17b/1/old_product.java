public static Page<User> findUsers(int pageNum, String loginId) {
        OrderParams orderParams = new OrderParams().add("loginId", Direction.ASC);
        SearchParams searchParams = new SearchParams();
        if(loginId != null) searchParams.add("loginId", loginId, Matching.CONTAINS);
        return FinderTemplate.getPage(orderParams, searchParams, find, USER_COUNT_PER_PAGE, pageNum);
    }