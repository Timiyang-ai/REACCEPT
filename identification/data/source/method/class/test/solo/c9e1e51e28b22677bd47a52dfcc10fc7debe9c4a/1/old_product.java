public void updatePreference(final JSONObject preference) throws ServiceException {
        @SuppressWarnings("unchecked")
        final Iterator<String> keys = preference.keys();

        while (keys.hasNext()) {
            final String key = keys.next();

            if (preference.isNull(key)) {
                throw new ServiceException("A value is null of preference[key=" + key + "]");
            }
        }

        final Transaction transaction = optionRepository.beginTransaction();

        try {
            final String skinDirName = preference.getString(Skin.SKIN_DIR_NAME);
            final String skinName = Latkes.getSkinName(skinDirName);

            preference.put(Skin.SKIN_NAME, skinName);
            final Set<String> skinDirNames = Skins.getSkinDirNames();
            final JSONArray skinArray = new JSONArray();

            for (final String dirName : skinDirNames) {
                final JSONObject skin = new JSONObject();

                skinArray.put(skin);

                final String name = Latkes.getSkinName(dirName);

                skin.put(Skin.SKIN_NAME, name);
                skin.put(Skin.SKIN_DIR_NAME, dirName);
            }

            preference.put(Skin.SKINS, skinArray.toString());

            final String timeZoneId = preference.getString(TIME_ZONE_ID);
            TimeZones.setTimeZone(timeZoneId);

            preference.put(Preference.SIGNS, preference.get(Preference.SIGNS).toString());

            final JSONObject oldPreference = preferenceQueryService.getPreference();
            final String adminEmail = oldPreference.getString(ADMIN_EMAIL);
            preference.put(ADMIN_EMAIL, adminEmail);

            final String version = oldPreference.optString(VERSION);
            preference.put(VERSION, version);

            final String localeString = preference.getString(Preference.LOCALE_STRING);
            LOGGER.log(Level.DEBUG, "Current locale[string={0}]", localeString);
            Latkes.setLocale(new Locale(Locales.getLanguage(localeString), Locales.getCountry(localeString)));

            final JSONObject adminEmailOpt = optionRepository.get(Option.ID_C_ADMIN_EMAIL);
            adminEmailOpt.put(Option.OPTION_VALUE, adminEmail);
            optionRepository.update(Option.ID_C_ADMIN_EMAIL, adminEmailOpt);

            final JSONObject allowVisitDraftViaPermalinkOpt = optionRepository.get(Option.ID_C_ALLOW_VISIT_DRAFT_VIA_PERMALINK);
            allowVisitDraftViaPermalinkOpt.put(Option.OPTION_VALUE, preference.optString(Option.ID_C_ALLOW_VISIT_DRAFT_VIA_PERMALINK));
            optionRepository.update(Option.ID_C_ALLOW_VISIT_DRAFT_VIA_PERMALINK, allowVisitDraftViaPermalinkOpt);

            final JSONObject allowRegisterOpt = optionRepository.get(Option.ID_C_ALLOW_REGISTER);
            allowRegisterOpt.put(Option.OPTION_VALUE, preference.optString(Option.ID_C_ALLOW_REGISTER));
            optionRepository.update(Option.ID_C_ALLOW_REGISTER, allowRegisterOpt);

            final JSONObject articleListDisplayCountOpt = optionRepository.get(Option.ID_C_ARTICLE_LIST_DISPLAY_COUNT);
            articleListDisplayCountOpt.put(Option.OPTION_VALUE, preference.optString(Option.ID_C_ARTICLE_LIST_DISPLAY_COUNT));
            optionRepository.update(Option.ID_C_ARTICLE_LIST_DISPLAY_COUNT, articleListDisplayCountOpt);

            final JSONObject articleListPaginationWindowSizeOpt = optionRepository.get(Option.ID_C_ARTICLE_LIST_PAGINATION_WINDOW_SIZE);
            articleListPaginationWindowSizeOpt.put(Option.OPTION_VALUE, preference.optString(Option.ID_C_ARTICLE_LIST_PAGINATION_WINDOW_SIZE));
            optionRepository.update(Option.ID_C_ARTICLE_LIST_PAGINATION_WINDOW_SIZE, articleListPaginationWindowSizeOpt);

            final JSONObject articleListStyleOpt = optionRepository.get(Option.ID_C_ARTICLE_LIST_STYLE);
            articleListStyleOpt.put(Option.OPTION_VALUE, preference.optString(Option.ID_C_ARTICLE_LIST_STYLE));
            optionRepository.update(Option.ID_C_ARTICLE_LIST_STYLE, articleListStyleOpt);

            final JSONObject blogSubtitleOpt = optionRepository.get(Option.ID_C_BLOG_SUBTITLE);
            blogSubtitleOpt.put(Option.OPTION_VALUE, preference.optString(Option.ID_C_BLOG_SUBTITLE));
            optionRepository.update(Option.ID_C_BLOG_SUBTITLE, blogSubtitleOpt);

            final JSONObject blogTitleOpt = optionRepository.get(Option.ID_C_BLOG_TITLE);
            blogTitleOpt.put(Option.OPTION_VALUE, preference.optString(Option.ID_C_BLOG_TITLE));
            optionRepository.update(Option.ID_C_BLOG_TITLE, blogTitleOpt);

            final JSONObject commentableOpt = optionRepository.get(Option.ID_C_COMMENTABLE);
            commentableOpt.put(Option.OPTION_VALUE, preference.optString(Option.ID_C_COMMENTABLE));
            optionRepository.update(Option.ID_C_COMMENTABLE, commentableOpt);

            final JSONObject editorTypeOpt = optionRepository.get(Option.ID_C_EDITOR_TYPE);
            editorTypeOpt.put(Option.OPTION_VALUE, preference.optString(Option.ID_C_EDITOR_TYPE));
            optionRepository.update(Option.ID_C_EDITOR_TYPE, editorTypeOpt);

            final JSONObject enableArticleUpdateHintOpt = optionRepository.get(Option.ID_C_ENABLE_ARTICLE_UPDATE_HINT);
            enableArticleUpdateHintOpt.put(Option.OPTION_VALUE, preference.optString(Option.ID_C_ENABLE_ARTICLE_UPDATE_HINT));
            optionRepository.update(Option.ID_C_ENABLE_ARTICLE_UPDATE_HINT, enableArticleUpdateHintOpt);

            final JSONObject externalRelevantArticlesDisplayCountOpt = optionRepository.get(Option.ID_C_EXTERNAL_RELEVANT_ARTICLES_DISPLAY_CNT);
            externalRelevantArticlesDisplayCountOpt.put(Option.OPTION_VALUE, preference.optString(Option.ID_C_EXTERNAL_RELEVANT_ARTICLES_DISPLAY_CNT));
            optionRepository.update(Option.ID_C_EXTERNAL_RELEVANT_ARTICLES_DISPLAY_CNT, externalRelevantArticlesDisplayCountOpt);

            final JSONObject feedOutputCntOpt = optionRepository.get(Option.ID_C_FEED_OUTPUT_CNT);
            feedOutputCntOpt.put(Option.OPTION_VALUE, preference.optString(Option.ID_C_FEED_OUTPUT_CNT));
            optionRepository.update(Option.ID_C_FEED_OUTPUT_CNT, feedOutputCntOpt);

            final JSONObject feedOutputModeOpt = optionRepository.get(Option.ID_C_FEED_OUTPUT_MODE);
            feedOutputModeOpt.put(Option.OPTION_VALUE, preference.optString(Option.ID_C_FEED_OUTPUT_MODE));
            optionRepository.update(Option.ID_C_FEED_OUTPUT_MODE, feedOutputModeOpt);

            final JSONObject footerContentOpt = optionRepository.get(Option.ID_C_FOOTER_CONTENT);
            footerContentOpt.put(Option.OPTION_VALUE, preference.optString(Option.ID_C_FOOTER_CONTENT));
            optionRepository.update(Option.ID_C_FOOTER_CONTENT, footerContentOpt);

            final JSONObject htmlHeadOpt = optionRepository.get(Option.ID_C_HTML_HEAD);
            htmlHeadOpt.put(Option.OPTION_VALUE, preference.optString(Option.ID_C_HTML_HEAD));
            optionRepository.update(Option.ID_C_HTML_HEAD, htmlHeadOpt);

            final JSONObject keyOfSoloOpt = optionRepository.get(Option.ID_C_KEY_OF_SOLO);
            keyOfSoloOpt.put(Option.OPTION_VALUE, preference.optString(Option.ID_C_KEY_OF_SOLO));
            optionRepository.update(Option.ID_C_KEY_OF_SOLO, keyOfSoloOpt);

            final JSONObject localeStringOpt = optionRepository.get(Option.ID_C_LOCALE_STRING);
            localeStringOpt.put(Option.OPTION_VALUE, preference.optString(Option.ID_C_LOCALE_STRING));
            optionRepository.update(Option.ID_C_LOCALE_STRING, localeStringOpt);

            final JSONObject metaDescriptionOpt = optionRepository.get(Option.ID_C_META_DESCRIPTION);
            metaDescriptionOpt.put(Option.OPTION_VALUE, preference.optString(Option.ID_C_META_DESCRIPTION));
            optionRepository.update(Option.ID_C_META_DESCRIPTION, metaDescriptionOpt);

            final JSONObject metaKeywordsOpt = optionRepository.get(Option.ID_C_META_KEYWORDS);
            metaKeywordsOpt.put(Option.OPTION_VALUE, preference.optString(Option.ID_C_META_KEYWORDS));
            optionRepository.update(Option.ID_C_META_KEYWORDS, metaKeywordsOpt);

            final JSONObject mostCommentArticleDisplayCountOpt = optionRepository.get(Option.ID_C_MOST_COMMENT_ARTICLE_DISPLAY_CNT);
            mostCommentArticleDisplayCountOpt.put(Option.OPTION_VALUE, preference.optString(Option.ID_C_MOST_COMMENT_ARTICLE_DISPLAY_CNT));
            optionRepository.update(Option.ID_C_MOST_COMMENT_ARTICLE_DISPLAY_CNT, mostCommentArticleDisplayCountOpt);

            final JSONObject mostUsedTagDisplayCountOpt = optionRepository.get(Option.ID_C_MOST_USED_TAG_DISPLAY_CNT);
            mostUsedTagDisplayCountOpt.put(Option.OPTION_VALUE, preference.optString(Option.ID_C_MOST_USED_TAG_DISPLAY_CNT));
            optionRepository.update(Option.ID_C_MOST_USED_TAG_DISPLAY_CNT, mostUsedTagDisplayCountOpt);

            final JSONObject mostViewArticleDisplayCountOpt = optionRepository.get(Option.ID_C_MOST_VIEW_ARTICLE_DISPLAY_CNT);
            mostViewArticleDisplayCountOpt.put(Option.OPTION_VALUE, preference.optString(Option.ID_C_MOST_VIEW_ARTICLE_DISPLAY_CNT));
            optionRepository.update(Option.ID_C_MOST_VIEW_ARTICLE_DISPLAY_CNT, mostViewArticleDisplayCountOpt);

            final JSONObject noticeBoardOpt = optionRepository.get(Option.ID_C_NOTICE_BOARD);
            noticeBoardOpt.put(Option.OPTION_VALUE, preference.optString(Option.ID_C_NOTICE_BOARD));
            optionRepository.update(Option.ID_C_NOTICE_BOARD, noticeBoardOpt);

            final JSONObject randomArticlesDisplayCountOpt = optionRepository.get(Option.ID_C_RANDOM_ARTICLES_DISPLAY_CNT);
            randomArticlesDisplayCountOpt.put(Option.OPTION_VALUE, preference.optString(Option.ID_C_RANDOM_ARTICLES_DISPLAY_CNT));
            optionRepository.update(Option.ID_C_RANDOM_ARTICLES_DISPLAY_CNT, randomArticlesDisplayCountOpt);

            final JSONObject recentArticleDisplayCountOpt = optionRepository.get(Option.ID_C_RECENT_ARTICLE_DISPLAY_CNT);
            recentArticleDisplayCountOpt.put(Option.OPTION_VALUE, preference.optString(Option.ID_C_RECENT_ARTICLE_DISPLAY_CNT));
            optionRepository.update(Option.ID_C_RECENT_ARTICLE_DISPLAY_CNT, recentArticleDisplayCountOpt);

            final JSONObject recentCommentDisplayCountOpt = optionRepository.get(Option.ID_C_RECENT_COMMENT_DISPLAY_CNT);
            recentCommentDisplayCountOpt.put(Option.OPTION_VALUE, preference.optString(Option.ID_C_RECENT_COMMENT_DISPLAY_CNT));
            optionRepository.update(Option.ID_C_RECENT_COMMENT_DISPLAY_CNT, recentCommentDisplayCountOpt);

            final JSONObject relevantArticlesDisplayCountOpt = optionRepository.get(Option.ID_C_RELEVANT_ARTICLES_DISPLAY_CNT);
            relevantArticlesDisplayCountOpt.put(Option.OPTION_VALUE, preference.optString(Option.ID_C_RELEVANT_ARTICLES_DISPLAY_CNT));
            optionRepository.update(Option.ID_C_RELEVANT_ARTICLES_DISPLAY_CNT, relevantArticlesDisplayCountOpt);

            final JSONObject replyNotiTplBodyOpt = optionRepository.get(Option.ID_C_REPLY_NOTI_TPL_BODY);
            replyNotiTplBodyOpt.put(Option.OPTION_VALUE, preference.optString(Option.ID_C_REPLY_NOTI_TPL_BODY));
            optionRepository.update(Option.ID_C_REPLY_NOTI_TPL_BODY, replyNotiTplBodyOpt);

            final JSONObject replyNotiTplSubjectOpt = optionRepository.get(Option.ID_C_REPLY_NOTI_TPL_SUBJECT);
            replyNotiTplSubjectOpt.put(Option.OPTION_VALUE, preference.optString(Option.ID_C_REPLY_NOTI_TPL_SUBJECT));
            optionRepository.update(Option.ID_C_REPLY_NOTI_TPL_SUBJECT, replyNotiTplSubjectOpt);

            final JSONObject signsOpt = optionRepository.get(Option.ID_C_SIGNS);
            signsOpt.put(Option.OPTION_VALUE, preference.optString(Option.ID_C_SIGNS));
            optionRepository.update(Option.ID_C_SIGNS, signsOpt);

            final JSONObject skinDirNameOpt = optionRepository.get(Option.ID_C_SKIN_DIR_NAME);
            skinDirNameOpt.put(Option.OPTION_VALUE, preference.optString(Option.ID_C_SKIN_DIR_NAME));
            optionRepository.update(Option.ID_C_SKIN_DIR_NAME, skinDirNameOpt);

            final JSONObject skinNameOpt = optionRepository.get(Option.ID_C_SKIN_NAME);
            skinNameOpt.put(Option.OPTION_VALUE, preference.optString(Option.ID_C_SKIN_NAME));
            optionRepository.update(Option.ID_C_SKIN_NAME, skinNameOpt);

            final JSONObject skinsOpt = optionRepository.get(Option.ID_C_SKINS);
            skinsOpt.put(Option.OPTION_VALUE, preference.optString(Option.ID_C_SKINS));
            optionRepository.update(Option.ID_C_SKINS, skinsOpt);

            final JSONObject timeZoneIdOpt = optionRepository.get(Option.ID_C_TIME_ZONE_ID);
            timeZoneIdOpt.put(Option.OPTION_VALUE, preference.optString(Option.ID_C_TIME_ZONE_ID));
            optionRepository.update(Option.ID_C_TIME_ZONE_ID, timeZoneIdOpt);

            final JSONObject versionOpt = optionRepository.get(Option.ID_C_VERSION);
            versionOpt.put(Option.OPTION_VALUE, preference.optString(Option.ID_C_VERSION));
            optionRepository.update(Option.ID_C_VERSION, versionOpt);

            transaction.commit();

            final ServletContext servletContext = SoloServletListener.getServletContext();

            Templates.MAIN_CFG.setServletContextForTemplateLoading(servletContext, "/skins/" + skinDirName);
        } catch (final Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }

            LOGGER.log(Level.ERROR, "Updates preference failed", e);
            throw new ServiceException(langPropsService.get("updateFailLabel"));
        }

        LOGGER.log(Level.DEBUG, "Updates preference successfully");
    }