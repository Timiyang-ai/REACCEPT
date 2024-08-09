@Aspect(advice = org.support.project.ormapping.transaction.Transaction.class)
    public void notifyEvents() {
        MailConfigsDao mailConfigsDao = MailConfigsDao.get();
        MailConfigsEntity mailConfigsEntity = mailConfigsDao.selectOnKey(AppConfig.get().getSystemName());
        if (mailConfigsEntity == null) {
            // メールの設定が登録されていなければ、送信処理は終了
            LOG.info("mail config is not exists.");
            return;
        }
        
        // 登録されているイベントを一週間分取得
        // タイムゾーンを考慮して取得するため、少し範囲広めで取得する。
        // GMT で先週の土曜〜次週の月曜
        LoginedUser admin = UserLogicEx.get().getAdminUser(); // バッチ起動であるため、管理者権限でデータを取得する
        
        TimeZone gmt = TimeZone.getTimeZone("GMT");
        Calendar now = Calendar.getInstance(gmt);
        Calendar start = Calendar.getInstance(gmt);
        Calendar end = Calendar.getInstance(gmt);
        
        start.set(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DATE), 0, 0, 0);
        start.set(Calendar.MILLISECOND, 0);
        LOG.debug("曜日: " + now.get(Calendar.DAY_OF_WEEK));
        int diff = now.get(Calendar.DAY_OF_WEEK);
        start.add(Calendar.DATE, -diff);
        end.setTime(start.getTime());
        end.add(Calendar.DATE, 9);
        
        EventsLogic.get().logging(now);
        EventsLogic.get().logging(start);
        EventsLogic.get().logging(end);
        
        List<EventsEntity> events = EventsDao.get().selectAccessAbleEvents(start, end, admin);
        
        for (EventsEntity event : events) {
            if (event.getNotifyStatus() == null) {
                event.setNotifyStatus(NOTIFY_STATUS_NOT_SEND);
            }
            if (StringUtils.isEmpty(event.getTimeZone())) {
                event.setTimeZone("GMT");
            }
            KnowledgesEntity knowledge = KnowledgesDao.get().selectOnKey(event.getKnowledgeId());
            if (knowledge == null) {
                continue;
            }
            List<KnowledgeItemValuesEntity> values = KnowledgeItemValuesDao.get().selectOnKnowledgeId(event.getKnowledgeId());
            notifyMin(event, knowledge, values, now, mailConfigsEntity);
            notifyDate(event, knowledge, values, now, mailConfigsEntity);
            notifyWeek(event, knowledge, values, now, mailConfigsEntity);
        }

    }