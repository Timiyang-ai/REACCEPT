public static Result login() {
		Form<User> userForm = form(User.class).bindFromRequest();
		if(userForm.hasErrors()) {
			return badRequest(login.render("title.login", userForm));
		}
		User sourceUser = form(User.class).bindFromRequest().get();

		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);

        Subject currentUser = SecurityUtils.getSubject();
        if(!currentUser.isAuthenticated()) {
        	UsernamePasswordToken token = new UsernamePasswordToken(sourceUser.loginId,
    				sourceUser.password);
        	token.setRememberMe(sourceUser.rememberMe);

        	//Object principal = token.getPrincipal();

        	try {
                currentUser.login(token);
            } catch (UnknownAccountException uae) {
            	Logger.info("There is no user with username of " + token.getPrincipal());
            } catch (IncorrectCredentialsException ice) {
            	Logger.info("Password for account " + token.getPrincipal() + " was incorrect!");
            } catch (LockedAccountException lae) {
            	Logger.info("The account for username " + token.getPrincipal() + " is locked.  " +
                        "Please contact your administrator to unlock it.");
            }
            // ... catch more exceptions here (maybe custom ones specific to your application?
            catch (AuthenticationException ae) {
                //unexpected condition?  error?
            }
        }

		User authenticate = authenticateWithPlainPassword(sourceUser.loginId, sourceUser.password);

		if(authenticate!=null) {
			setUserInfoInSession(authenticate);
			if (sourceUser.rememberMe) {
				setupRememberMe(authenticate);
			}
			return redirect(routes.Application.index());
		}

		flash(Constants.WARNING, "user.login.failed");
		return redirect(routes.UserApp.loginForm());
	}