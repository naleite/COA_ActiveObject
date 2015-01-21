package async;

/**
 * Created by naleite on 15/1/7.
 */
public interface Subject {
    public void attach(Observer o);

    public void detach(Observer o);

    public void start();
}
