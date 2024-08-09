public Boolean subscribe(String callback, String topic, String verify, long lease_seconds, String secret,
        String verify_token) {
        Logger.getLogger(Hub.class.getName()).log(Level.FINE, "{0} wants to subscribe to {1}", new Object[]{callback, topic});
        try {
            try {
                assert callback != null : "Callback URL is required.";
                assert topic != null : "Topic URL is required.";

                URI uri = new URI(callback);
                assert this.validSchemes.contains(uri.getScheme()) : "Not a valid protocol " + uri.getScheme();
                assert this.validPorts.isEmpty() || this.validPorts.contains(uri.getPort()) : "Not a valid port " +
                uri.getPort();
                assert this.validTopics.isEmpty() || this.validTopics.contains(topic) : "Not a supported topic " +
                topic;
            } catch (URISyntaxException ex) {
                assert false : "Not a valid URI " + callback;
            }
            assert (verify != null) &&
            (verify.equals(Subscriber.VERIFY_ASYNC) || verify.equals(Subscriber.VERIFY_SYNC)) : "Unexpected verify value " +
            verify;

            final Subscriber subscriber = new Subscriber();
            subscriber.setCallback(callback);
            subscriber.setLeaseSeconds(lease_seconds);
            subscriber.setSecret(secret);
            subscriber.setTopic(topic);
            subscriber.setVerify(verify);
            subscriber.setVertifyToken(verify_token);

            VerificationCallback verified = new VerificationCallback() {
                    @Override
                    public void onVerify(boolean verified) {
                        if (verified) {
                            Logger.getLogger(Hub.class.getName()).log(Level.FINE, "Verified {0} subscribed to {1}", new Object[]{subscriber.getCallback(), subscriber.getTopic()});
                            dao.addSubscriber(subscriber);
                        }
                    }
                };

            if (Subscriber.VERIFY_SYNC.equals(subscriber.getVerify())) {
                boolean result = verifier.verifySubcribeSyncronously(subscriber);
                verified.onVerify(result);

                return result;
            } else {
                verifier.verifySubscribeAsyncronously(subscriber, verified);

                return null;
            }
        } catch (AssertionError ae) {
            throw new HttpStatusCodeException(400, ae.getMessage(), ae);
        } catch (Exception e) {
            throw new HttpStatusCodeException(500, e.getMessage(), e);
        }
    }