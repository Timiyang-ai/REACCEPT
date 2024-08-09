    @Test
    public void getCapturePixelColor() throws InterruptedException {
        assumeThat("skipping: screen capture on macOS uses configured display's color profile",
                System.getenv("TRAVIS_OS_NAME"), is(not(equalTo("osx"))));

        // given:
        CountDownLatch captureColorLatch = new CountDownLatch(1);

        // when:
        CompletableFuture<Color> captureColorFutureResult = new CompletableFuture<>();
        Platform.runLater(() -> captureColorFutureResult.complete(robotAdapter.getCapturePixelColor(regionPoint)));

        // then:
        captureColorFutureResult.whenComplete((pixelColor, throwable) -> {
            if (throwable != null) {
                fail("JavafxRobotAdapter.getCapturePixelColor(..) should not have completed exceptionally");
            }
            else {
                assertThat(pixelColor, is(Color.MAGENTA));
                captureColorLatch.countDown();
            }
        });

        assertThat(captureColorLatch.await(3, TimeUnit.SECONDS), is(true));
    }