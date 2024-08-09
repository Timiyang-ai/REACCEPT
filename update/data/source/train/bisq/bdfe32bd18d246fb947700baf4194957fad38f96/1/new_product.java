@Override
    public boolean isValidForAddOperation() {
        if (!this.isSignatureValid())
            return false;

        MailboxStoragePayload mailboxStoragePayload = this.getMailboxStoragePayload();

        // Verify the Entry.receiversPubKey matches the Payload.ownerPubKey. This is a requirement for removal
        if (!mailboxStoragePayload.getOwnerPubKey().equals(this.receiversPubKey)) {
            log.debug("Entry receiversPubKey does not match payload owner which is a requirement for adding MailboxStoragePayloads");
            return false;
        }

        boolean result = mailboxStoragePayload.getSenderPubKeyForAddOperation() != null &&
                mailboxStoragePayload.getSenderPubKeyForAddOperation().equals(this.getOwnerPubKey());

        if (!result) {
            String res1 = this.toString();
            String res2 = "null";
            if (mailboxStoragePayload != null && mailboxStoragePayload.getOwnerPubKey() != null)
                res2 = Utilities.encodeToHex(mailboxStoragePayload.getSenderPubKeyForAddOperation().getEncoded(),true);

            log.warn("ProtectedMailboxStorageEntry::isValidForAddOperation() failed. " +
                    "Entry owner does not match sender key in payload:\nProtectedStorageEntry=%{}\n" +
                    "SenderPubKeyForAddOperation=%{}", res1, res2);
        }

        return result;
    }