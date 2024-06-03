package main;

public class MousePosition {
    private int x;
    private int y;
    private boolean updated = false;

    public synchronized void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
        this.updated = true;
    }

    public synchronized int getX() {
        return x;
    }

    public synchronized int getY() {
        return y;
    }

    public synchronized boolean isUpdated() {
        return updated;
    }

    public synchronized void clearUpdated() {
        this.updated = false;
    }
}
