@VisibleForTesting
    void connectToDevice(Context context) throws IOException {
        // Connect on transport layer
        mCardCapabilities = new CardCapabilities();

        mTransport.connect();

        determineTokenType();

        CommandApdu select = commandFactory.createSelectFileOpenPgpCommand();
        ResponseApdu response = communicate(select);  // activate connection

        if (!response.isSuccess()) {
            throw new CardException("Initialization failed!", response.getSw());
        }

        refreshConnectionCapabilities();

        mPw1ValidatedForSignature = false;
        mPw1ValidatedForDecrypt = false;
        mPw3Validated = false;

        if (mOpenPgpCapabilities.isHasSCP11bSM()) {
            try {
                SCP11bSecureMessaging.establish(this, context, commandFactory);
            } catch (SecureMessagingException e) {
                mSecureMessaging = null;
                Log.e(Constants.TAG, "failed to establish secure messaging", e);
            }
        }
    }