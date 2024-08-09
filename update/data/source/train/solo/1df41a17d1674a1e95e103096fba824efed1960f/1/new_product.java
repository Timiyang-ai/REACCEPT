public void updateUser(final JSONObject requestJSONObject) throws ServiceException {
        final Transaction transaction = userRepository.beginTransaction();

        try {
            final String oldUserId = requestJSONObject.optString(Keys.OBJECT_ID);
            final JSONObject oldUser = userRepository.get(oldUserId);

            if (null == oldUser) {
                throw new ServiceException(langPropsService.get("updateFailLabel"));
            }

            final String userNewEmail = requestJSONObject.optString(User.USER_EMAIL).toLowerCase().trim();
            // Check email is whether duplicated
            final JSONObject mayBeAnother = userRepository.getByEmail(userNewEmail);

            if (null != mayBeAnother && !mayBeAnother.optString(Keys.OBJECT_ID).equals(oldUserId)) {
                // Exists someone else has the save email as requested
                throw new ServiceException(langPropsService.get("duplicatedEmailLabel"));
            }

            // Update
            final String userName = requestJSONObject.optString(User.USER_NAME);
            final String userPassword = requestJSONObject.optString(User.USER_PASSWORD);

            oldUser.put(User.USER_EMAIL, userNewEmail);
            oldUser.put(User.USER_NAME, userName);

            final boolean mybeHashed = HASHED_PASSWORD_LENGTH == userPassword.length();
            final String newHashedPassword = MD5.hash(userPassword);
            final String oldHashedPassword = oldUser.optString(User.USER_PASSWORD);

            if (!mybeHashed || (!oldHashedPassword.equals(userPassword) && !oldHashedPassword.equals(newHashedPassword))) {
                oldUser.put(User.USER_PASSWORD, newHashedPassword);
            }

            final String userRole = requestJSONObject.optString(User.USER_ROLE);

            if (!Strings.isEmptyOrNull(userRole)) {
                oldUser.put(User.USER_ROLE, userRole);
            }

            final String userURL = requestJSONObject.optString(User.USER_URL);

            if (!Strings.isEmptyOrNull(userURL)) {
                oldUser.put(User.USER_URL, userURL);
            }

            userRepository.update(oldUserId, oldUser);
            transaction.commit();
        } catch (final RepositoryException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }

            LOGGER.log(Level.SEVERE, "Updates a user failed", e);
            throw new ServiceException(e);
        }
    }