public static Page<User> findUsers(int pageNum, String query, UserState state) {
        ExpressionList<User> el = User.find.where();
        el.ne("id",SITE_MANAGER_ID);
        el.ne("loginId",anonymous.loginId);
        el.eq("state", state);

        if(StringUtils.isNotBlank(query)) {
            el = el.disjunction();
            el = el.icontains("loginId", query).icontains("name", query).icontains("email", query);
            el.endJunction();
        }

        return el.findPagingList(USER_COUNT_PER_PAGE).getPage(pageNum);
    }