package RPC.rpc_stub;

import RPC.common.IUserService;
import RPC.common.IUserServiceImpl;
import RPC.common.User;

import java.io.*;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static boolean isrunning = true;

    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(8888);
        while (isrunning) {
            Socket s = ss.accept();
            proccess(s);
            s.close();
        }
        ss.close();
    }

    private static void proccess(Socket s) throws Exception {
        InputStream in = s.getInputStream();
        OutputStream out = s.getOutputStream();
        ObjectInputStream oos = new ObjectInputStream(in);
        DataOutputStream dos = new DataOutputStream(out);

        String methodName = oos.readUTF();
        Class[] paramterTypes = (Class [])oos.readObject();
        Object[] args = (Object [])oos.readObject();

        IUserService service = new IUserServiceImpl();
        Method method = service.getClass().getMethod(methodName, paramterTypes);
        User user = (User)method.invoke(service, args);

        dos.writeInt(user.getId());
        dos.writeUTF(user.getUserName());
        dos.flush();
    }
}
