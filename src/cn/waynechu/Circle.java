package cn.waynechu;

/**
 * @author waynechu
 * Created 2018-03-22 12:01
 */
public class Circle {
    /** 圆心 **/
    public int x, y;
    /** 半径 **/
    private int r;
    /** 速度 **/
    public int vx, vy;

    public Circle(int x, int y, int r, int vx, int vy) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.vx = vx;
        this.vy = vy;
    }

    public int getR() {
        return r;
    }

    public void move(int minX, int minY, int canvasWidth, int canvasHeight) {
        x += vx;
        y += vy;
        checkCollision(minX, minY, canvasWidth, canvasHeight);
    }

    /**
     * 边界碰撞检测
     *
     * @param minX
     * @param minY
     * @param maxX
     * @param maxY
     */
    private void checkCollision(int minX, int minY, int maxX, int maxY) {
        if (x - r <= minX) {
            x = r;
            vx = -vx;
        }
        if (x + r >= maxX) {
            x = maxX - r;
            vx = -vx;
        }
        if (y - r <= minY) {
            y = r;
            vy = -vy;
        }
        if (y + r >= maxY) {
            y = maxY - r;
            vy = -vy;
        }
    }
}
