protected KnowledgesEntity insertKnowledge(String title, LoginedUser loginedUser) throws Exception {
        int publicFlag = KnowledgeLogic.PUBLIC_FLAG_PUBLIC;
        int typeId = TemplateLogic.TYPE_ID_KNOWLEDGE;
        return insertKnowledge(title, loginedUser, typeId, publicFlag);
    }