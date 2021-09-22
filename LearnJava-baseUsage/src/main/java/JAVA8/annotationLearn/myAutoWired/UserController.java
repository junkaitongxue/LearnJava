package JAVA8.annotationLearn.myAutoWired;

public class UserController {
    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @MyAutoWired
    private UserService userService;


}
