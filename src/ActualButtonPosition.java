/**
 * @Author Tobias Fetzer 198318, Simon Stratemeier 199067
 * @Version: 1.0
 * @Date: 01/05/18
 * The position of the non rotated Button Button
 */
public class ActualButtonPosition {
    public int x;
    public int y;

    public ActualButtonPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return x + "/" + y;
    }
}
