@Test
    public void testNotify() throws Exception {
        final AtomicReference<Boolean> notified = new AtomicReference<Boolean>(false);
        NotifyListener listener1 = urls -> notified.set(Boolean.TRUE);
        URL url1 = new URL("dubbo", "192.168.0.1", 2200, parametersConsumer);
        abstractRegistry.subscribe(url1, listener1);
        NotifyListener listener2 = urls -> notified.set(Boolean.TRUE);
        URL url2 = new URL("dubbo", "192.168.0.2", 2201, parametersConsumer);
        abstractRegistry.subscribe(url2, listener2);
        NotifyListener listener3 = urls -> notified.set(Boolean.TRUE);
        URL url3 = new URL("dubbo", "192.168.0.3", 2202, parametersConsumer);
        abstractRegistry.subscribe(url3, listener3);
        List<URL> urls = new ArrayList<>();
        urls.add(url1);
        urls.add(url2);
        urls.add(url3);
        abstractRegistry.notify(url1, listener1, urls);
        Map<URL, Map<String, List<URL>>> map = abstractRegistry.getNotified();
        MatcherAssert.assertThat(true, Matchers.equalTo(map.containsKey(url1)));
        MatcherAssert.assertThat(false, Matchers.equalTo(map.containsKey(url2)));
        MatcherAssert.assertThat(false, Matchers.equalTo(map.containsKey(url3)));
    }