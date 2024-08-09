@Post(subscribeToken = "account", checkReqToken = true)
    public Boundary update() throws ParseException {
        SystemConfigsDao systemConfigsDao = SystemConfigsDao.get();

        SystemConfigsEntity userAddType = systemConfigsDao.selectOnKey(SystemConfig.USER_ADD_TYPE, AppConfig.get().getSystemName());
        if (userAddType == null) {
            userAddType = new SystemConfigsEntity(SystemConfig.USER_ADD_TYPE, AppConfig.get().getSystemName());
            userAddType.setConfigValue(SystemConfig.USER_ADD_TYPE_VALUE_ADMIN);
        }
        setAttribute("userAddType", userAddType.getConfigValue());

        LoginedUser loginedUser = super.getLoginedUser();
        if (loginedUser == null) {
            return sendError(HttpStatus.SC_401_UNAUTHORIZED, "");
        }

        Map<String, String> values = getParams();
        values.put("userId", String.valueOf(super.getLoginUserId()));
        values.put("rowId", "-"); // row_idは必須だが、画面からはこない
        if (StringUtils.isEmpty(getParam("password"))) {
            values.put("password", "-");
        }
        values.put("userName", super.sanitize(values.get("userName"))); // ユーザ名はXSS対策する

        UsersEntity user = new UsersEntity();
        List<ValidateError> errors = user.validate(values);
        if (!StringUtils.isEmpty(getParam("password"))) {
            if (!getParam("password").equals(getParam("confirm_password", String.class))) {
                ValidateError error = new ValidateError("knowledge.user.invalid.same.password");
                errors.add(error);
            }
        }

        if (errors.isEmpty()) {
            // エラーが無い場合のみ更新する
            // UsersEntity user = super.getParams(UsersEntity.class);
            UsersDao dao = UsersDao.get();
            user = dao.selectOnKey(getLoginUserId());
            if (user == null) {
                return sendError(HttpStatus.SC_400_BAD_REQUEST, "user is allready removed.");
            }
            if (user.getAuthLdap() != null && user.getAuthLdap().intValue() == INT_FLAG.ON.getValue()) {
                return sendError(HttpStatus.SC_400_BAD_REQUEST, "can not edit ldap user.");
            }
            if (userAddType.getConfigValue().equals(SystemConfig.USER_ADD_TYPE_VALUE_ADMIN)) {
                // ユーザ登録を管理者が行っている場合、メールアドレスは変更出来ない（変更用の画面も使えない）
                LOG.trace("USER_ADD_TYPE_VALUE_ADMIN");
            } else if (userAddType.getConfigValue().equals(SystemConfig.USER_ADD_TYPE_VALUE_APPROVE)) {
                // ユーザが自分で登録して管理者が承認の場合も変更出来ない（変更用の画面も使えない）
                LOG.trace("USER_ADD_TYPE_VALUE_APPROVE");
            } else if (userAddType.getConfigValue().equals(SystemConfig.USER_ADD_TYPE_VALUE_MAIL)) {
                // ダブルオプトインの場合も変更出来ない（変更用の画面で変更）
                LOG.trace("USER_ADD_TYPE_VALUE_MAIL");
            } else {
                user.setUserKey(getParam("userKey"));
            }
            user.setUserName(getParam("userName"));
            if (!StringUtils.isEmpty(getParam("password"))) {
                user.setPassword(getParam("password"));
                user.setEncrypted(false);
            }
            if (StringUtils.isEmpty(user.getMailAddress())) {
                if (StringUtils.isEmailAddress(user.getUserKey())) {
                    user.setMailAddress(user.getUserKey());
                }
            }
            dao.update(user);
        }
        String successMsg = "message.success.update";
        setResult(successMsg, errors);

        return forward("index.jsp");
    }