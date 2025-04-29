package devices;

public interface Controllable {
    void setParameter(String key, Object value);
    Object getParameter(String key);
}
