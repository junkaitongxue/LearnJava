package RPC.common;

public class IUserServiceImpl implements IUserService {
    @Override
    public User getUserNameById(int id) {
        return new User(123, "hello world");
    }
}
