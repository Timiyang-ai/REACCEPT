public static String linkToHtml(final String markdownText) {
        if (Strings.isEmptyOrNull(markdownText)) {
            return "";
        }

        String ret = getHTML(markdownText);
        if (null != ret) {
            return ret;
        }

        final ExecutorService pool = Executors.newSingleThreadExecutor();

        final long[] threadId = new long[1];

        final Callable<String> call = new Callable<String>() {
            @Override
            public String call() throws Exception {
                threadId[0] = Thread.currentThread().getId();

                String ret = LANG_PROPS_SERVICE.get("contentRenderFailedLabel");

                if (MARKED_AVAILABLE) {
                    ret = toHtmlByMarked(markdownText);

                    if (!StringUtils.startsWith(ret, "<p>")) {
                        ret = "<p>" + ret + "</p>";
                    }
                } else {
                    final PegDownProcessor pegDownProcessor
                            = new PegDownProcessor(Extensions.ALL_OPTIONALS | Extensions.ALL_WITH_OPTIONALS);

                    final RootNode node = pegDownProcessor.parseMarkdown(markdownText.toCharArray());
                    ret = new ToHtmlSerializer(new LinkRenderer(), Collections.<String, VerbatimSerializer>emptyMap(),
                            Arrays.asList(new ToHtmlSerializerPlugin[0])).toHtml(node);

                    if (!StringUtils.startsWith(ret, "<p>")) {
                        ret = "<p>" + ret + "</p>";
                    }

                    ret = formatMarkdown(ret);
                }

                // cache it
                putHTML(markdownText, ret);

                return ret;
            }
        };

        try {
            final Future<String> future = pool.submit(call);

            return future.get(MD_TIMEOUT, TimeUnit.MILLISECONDS);
        } catch (final TimeoutException e) {
            LOGGER.log(Level.ERROR, "Markdown timeout [md=" + markdownText + "]");

            final Set<Thread> threads = Thread.getAllStackTraces().keySet();
            for (final Thread thread : threads) {
                if (thread.getId() == threadId[0]) {
                    thread.stop();

                    break;
                }
            }
        } catch (final Exception e) {
            LOGGER.log(Level.ERROR, "Markdown failed [md=" + markdownText + "]", e);
        } finally {
            pool.shutdownNow();
        }

        return "";
    }