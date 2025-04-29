package core;

import java.io.Serializable;

public interface Action extends Serializable {
    void execute();
}
