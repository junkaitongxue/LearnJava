package multralProcess.demo01_scheduleExecutor;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DataExecutor implements Runnable {

    private String id;
    private String name;

    @Override
    public void run() {
        System.out.println("handling: " + id + "/" + name);
    }
}
