package Utility.MathUtils;

public class Vector3f {
    public float x;
    public float y;
    public float z;
    public Vector3f(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void add(float x, float y, float z) {
        this.x += x;
        this.y += y;
        this.z += z;
    }

    public void subtract(float x, float y, float z) {
        this.x -= x;
        this.y -= y;
        this.z -= z;
    }

    public float dotProduct(float x, float y, float z) {
        return this.x * x + this.y * y + this.z * z;
    }

    public Vector3f crossProduct(Vector3f vector) {
        float x = this.y * vector.z - this.z * vector.y;
        float y = this.z * vector.x - this.x * vector.z;
        float z = this.x * vector.y - this.y * vector.x;
        return new Vector3f(x, y, z);
    }

    public double getLength() {
        return Math.sqrt(x * x + y * y + z * z);
    }
}
