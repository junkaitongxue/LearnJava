package RPC.rpc_stub;

import RPC.common.IUserService;

public class client {
    public static void main(String[] args) throws Exception {
        IUserService service = Stub.getStub();
        System.out.println(service.getUserNameById(123));
    }
}
