@Test
    public void testNotifyEvents() throws Exception {
        KnowledgesEntity knowledge = new KnowledgesEntity();
        knowledge.setTitle("Sample1");
        knowledge.setContent("Test");
        knowledge.setTypeId(TemplateLogic.TYPE_ID_EVENT);
        
        TimeZone timezone = TimeZone.getTimeZone("GMT");
        Calendar now = Calendar.getInstance(timezone);
        int diff = now.get(Calendar.DAY_OF_WEEK);
        now.set(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DATE), 0, 0, 0);
        now.add(Calendar.DATE, -diff);
        now.add(Calendar.DATE, Calendar.THURSDAY);
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        
        TemplateMastersEntity template = TemplateMastersDao.get().selectWithItems(knowledge.getTypeId());
        List<TemplateItemsEntity> items = template.getItems();
        for (TemplateItemsEntity item : items) {
            if (item.getItemNo() == EventsLogic.ITEM_NO_DATE) {
                item.setItemValue(dateFormat.format(new Date(now.getTimeInMillis())));
            } else if (item.getItemNo() == EventsLogic.ITEM_NO_START) {
                item.setItemValue("11:00");
            } else if (item.getItemNo() == EventsLogic.ITEM_NO_END) {
                item.setItemValue("13:00");
            } else if (item.getItemNo() == EventsLogic.ITEM_NO_TIMEZONE) {
                item.setItemValue("GMT");
            } else if (item.getItemNo() == EventsLogic.ITEM_NO_THE_NUMBER_TO_BE_ACCEPTED) {
                item.setItemValue("3");
            } else if (item.getItemNo() == EventsLogic.ITEM_NO_PLACE) {
                item.setItemValue("第1会議室");
            }
        }
        
        KnowledgeData data = KnowledgeData.create(knowledge, "", "", "", null, null, template);
        knowledge = KnowledgeLogic.get().insert(data, loginedUser);
        
        // 参加登録
        ParticipantsEntity participant = new ParticipantsEntity(knowledge.getKnowledgeId(), loginedUser.getUserId());
        participant.setStatus(EventsLogic.STATUS_PARTICIPATION);
        ParticipantsDao.get().save(participant);
        
        // メール通知実行
        MailEventLogic.get().notifyEvents();
        
        // メールが1件登録されていること
        List<MailsEntity> mails = MailsDao.get().selectAll();
        Calendar today = Calendar.getInstance(timezone);
        EventsLogic.get().logging(today);
        if (today.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            Assert.assertEquals(1, mails.size());
        } else {
            Assert.assertEquals(0, mails.size());
        }
    }