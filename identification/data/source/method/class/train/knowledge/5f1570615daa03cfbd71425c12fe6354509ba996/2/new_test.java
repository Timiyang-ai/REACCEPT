@Test
    @Order(order = 3)
    public void testInsertKnowledge() throws Exception {
        LOG.info("記事投稿");
        AccessUser loginUser = getLoginUser("integration-test-user-01");
        DBUserPool.get().setUser(loginUser.getUserId()); // 操作ユーザのIDを指定
        
        KnowledgesEntity knowledge = super.insertKnowledge("integration-test-knowledge-01", loginUser);
        knowledgeId = knowledge.getKnowledgeId();
        
        NotifyQueuesEntity notify = NotifyQueuesDao.get().selectOnTypeAndId(QueueNotification.TYPE_KNOWLEDGE_INSERT, knowledgeId);
        Assert.assertNotNull(notify);
        NotifyMailBat.main(null);
        notify = NotifyQueuesDao.get().selectOnTypeAndId(QueueNotification.TYPE_KNOWLEDGE_INSERT, knowledgeId);
        Assert.assertNull(notify);

        NotificationsEntity notification = NotificationsDao.get().selectOnKey(new Long(1)); // 1件だけ通知が登録されているはず
        Assert.assertNotNull(notification);
        
        UsersEntity user = UsersDao.get().selectOnUserKey("integration-test-user-01");
        UserNotificationsEntity userNotification = UserNotificationsDao.get().selectOnKey(notification.getNo(), user.getUserId());
        Assert.assertNotNull(userNotification);
        user = UsersDao.get().selectOnUserKey("integration-test-user-02");
        userNotification = UserNotificationsDao.get().selectOnKey(notification.getNo(), user.getUserId());
        Assert.assertNotNull(userNotification);
        
        int count = MailsDao.get().selectCountAll();
        Assert.assertEquals(2, count);
    }