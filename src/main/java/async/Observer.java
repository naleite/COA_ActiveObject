package async;

/**
 * Created by naleite on 15/1/7.
 */
public interface Observer<T> {
    public void update(T subject);
}
