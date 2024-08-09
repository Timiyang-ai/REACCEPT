@RequestProcessing(value = "/get-article-content", method = HTTPRequestMethod.GET)
    public void getArticleContent(final HTTPRequestContext context, final HttpServletRequest request) {
        final String articleId = request.getParameter("id");

        if (Strings.isEmptyOrNull(articleId)) {
            return;
        }
        
        final TextHTMLRenderer renderer = new TextHTMLRenderer();

        context.setRenderer(renderer);

        String content;

        try {
            content = articleQueryService.getArticleContent(request, articleId);
        } catch (final ServiceException e) {
            LOGGER.log(Level.ERROR, "Can not get article content", e);
            return;
        }

        if (null == content) {
            return;
        }

        renderer.setContent(content);
    }