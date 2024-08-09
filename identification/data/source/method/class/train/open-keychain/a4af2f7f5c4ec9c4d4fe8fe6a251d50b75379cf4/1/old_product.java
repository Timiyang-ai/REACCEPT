private void connectToDevice(Context context) throws IOException {
        // Connect on transport layer
        mCardCapabilities = new CardCapabilities();

        mTransport.connect();

        // Connect on smartcard layer
        // Command APDU (page 51) for SELECT FILE command (page 29)
        CommandApdu select = commandFactory.createSelectFileOpenPgpCommand();
        ResponseApdu response = communicate(select);  // activate connection

        if (!response.isSuccess()) {
            throw new CardException("Initialization failed!", response.getSw());
        }

        mOpenPgpCapabilities = new OpenPgpCapabilities(getData(0x00, 0x6E));
        mCardCapabilities = new CardCapabilities(mOpenPgpCapabilities.getHistoricalBytes());

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