@Test
    @Order(order = 200)
    public void testUpdate() throws Exception {
        StubHttpServletRequest request = new StubHttpServletRequest();
        StubHttpServletResponse response = new StubHttpServletResponse(request);
        request.setServletPath("protect.knowledge/view_add");
        request.setMethod("get");
        DefaultAuthenticationLogicImpl auth = org.support.project.di.Container.getComp(DefaultAuthenticationLogicImpl.class);
        auth.setSession(POST_USER, request, response);
        
        ForwardBoundary boundary = invoke(request, response, ForwardBoundary.class);
        Assert.assertEquals("/WEB-INF/views/protect/knowledge/edit.jsp", PropertyUtil.getPrivateFeildOnReflection(String.class, boundary, "path"));

        String csrfToken = (String) request.getAttribute(HttpRequestCheckLogic.REQ_ID_KEY);
        Assert.assertNotNull(csrfToken);

        // 保存
        request.setServletPath("protect.knowledge/save");
        request.setMethod("post");
        request.addParameter("knowledgeId", String.valueOf(1));
        request.addParameter(HttpRequestCheckLogic.REQ_ID_KEY, csrfToken);
        request.addParameter("title", "タイトル");
        request.addParameter("content", "内容");
        request.addParameter("typeId", String.valueOf(TemplateLogic.TYPE_ID_BOOKMARK));
        request.addParameter("item_" + TemplateItemsDao.ITEM_ID_BOOKMARK_URL, "https://information-supportproject.org/");
        request.addParameter("publicFlag", String.valueOf(KnowledgeLogic.PUBLIC_FLAG_PROTECT));
        request.addParameter("updateContent", "true");
        
        AccessUser user = super.getLoginUser(USER1);
        request.addParameter("groups", TargetLogic.ID_PREFIX_USER + user.getUserId());
        
        JsonBoundary jsonBoundary = invoke(request, response, JsonBoundary.class);
        MessageResult sendObject = (MessageResult) jsonBoundary.getObj();
        LOG.info(sendObject);
        Assert.assertEquals(200, sendObject.getCode().intValue());
    }