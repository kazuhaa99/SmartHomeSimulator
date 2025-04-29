package devices;

import java.time.LocalTime;

public interface Schedulable {
    void schedule(LocalTime time);
}
