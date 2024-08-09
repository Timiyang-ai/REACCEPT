@Transactional
    public static Comment saveComment(IMAPMessage message, Resource target)
            throws MessagingException, MailHandlerException, IOException, NoSuchAlgorithmException {
        User author = IMAPMessageUtil.extractSender(message);

        if (!AccessControl.isProjectResourceCreatable(
                author, target.getProject(), target.getType())) {
            throw new PermissionDenied(cannotCreateMessage(author,
                    target.getProject(), target.getType()));
        }

        Content parsedMessage = extractContent(message);

        Comment comment = makeNewComment(target, author, parsedMessage.body);

        comment.save();

        Map<String, Attachment> relatedAttachments = saveAttachments(
                parsedMessage.attachments,
                comment.asResource());

        if (new ContentType(parsedMessage.type).match(MimeType.HTML)) {
            comment.contents = postprocessForHTML(comment.contents, relatedAttachments);
            comment.update();
        }

        new OriginalEmail(message.getMessageID(), comment.asResource()).save();

        // Add the event
        addEvent(NotificationEvent.forNewComment(comment, author),
                message.getAllRecipients(), author);

        return comment;
    }