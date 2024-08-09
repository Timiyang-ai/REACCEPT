public static Result login() {
        if (HttpUtil.isJSONPreferred(request())) {
            return loginByAjaxRequest();
        } else {
            return loginByFormRequest();
        }
    }