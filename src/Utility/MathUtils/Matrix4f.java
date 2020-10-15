package Utility.MathUtils;

import org.lwjgl.system.MemoryStack;

import java.nio.FloatBuffer;

import static java.lang.Math.*;

public class Matrix4f {
    float x0, y0, z0, dx;
    float x1, y1, z1, dy;
    float x2, y2, z2, dz;
    float x3, y3, z3, ff;

    public Matrix4f() {
        setIdentity();
    }

    public Matrix4f(Vector4f row0, Vector4f row1, Vector4f row2, Vector4f row3) {
        x0 = row0.x;
        y0 = row0.y;
        z0 = row0.z;
        dx = row0.w;
        x1 = row1.x;
        y1 = row1.y;
        z1 = row1.z;
        dy = row1.w;
        x2 = row2.x;
        y2 = row2.y;
        z2 = row2.z;
        dz = row2.w;
        x3 = row3.x;
        y3 = row3.y;
        z3 = row3.z;
        ff = row3.w;
    }

    public void setIdentity() {
        x0 = 1;
        y0 = 0;
        z0 = 0;
        dx = 0;
        x1 = 0;
        y1 = 1;
        z1 = 0;
        dy = 0;
        x2 = 0;
        y2 = 0;
        z2 = 1;
        dz = 0;
        x3 = 0;
        y3 = 0;
        z3 = 0;
        ff = 1;
    }

    public Vector4f multiplyVector4f(Vector4f vector4f) {
        float x = x0 * vector4f.x + y0 * vector4f.y + z0 * vector4f.z + dx * vector4f.w;
        float y = x1 * vector4f.x + y1 * vector4f.y + z1 * vector4f.z + dy * vector4f.w;
        float z = x2 * vector4f.x + y2 * vector4f.y + z2 * vector4f.z + dz * vector4f.w;
        float w = x3 * vector4f.x + y3 * vector4f.y + z3 * vector4f.z + ff * vector4f.w;
        return new Vector4f(x, y, z, w);
    }

    public Matrix4f multiplyMatrix4f(Matrix4f matrix4f) {
        Matrix4f multiplyMatrix = new Matrix4f();
        multiplyMatrix.x0 = this.x0 * matrix4f.x0 + this.y0 * matrix4f.x1 + this.z0 * matrix4f.x2 + this.dx * matrix4f.x3;
        multiplyMatrix.y0 = this.x0 * matrix4f.y0 + this.y0 * matrix4f.y1 + this.z0 * matrix4f.y2 + this.dx * matrix4f.y3;
        multiplyMatrix.z0 = this.x0 * matrix4f.z0 + this.y0 * matrix4f.z1 + this.z0 * matrix4f.z2 + this.dx * matrix4f.z3;
        multiplyMatrix.dx = this.x0 * matrix4f.dx + this.y0 * matrix4f.dy + this.z0 * matrix4f.dz + this.dx * matrix4f.ff;
        multiplyMatrix.x1 = this.x1 * matrix4f.x0 + this.y1 * matrix4f.x1 + this.z1 * matrix4f.x2 + this.dy * matrix4f.x3;
        multiplyMatrix.y1 = this.x1 * matrix4f.y0 + this.y1 * matrix4f.y1 + this.z1 * matrix4f.y2 + this.dy * matrix4f.y3;
        multiplyMatrix.z1 = this.x1 * matrix4f.z0 + this.y1 * matrix4f.z1 + this.z1 * matrix4f.z2 + this.dy * matrix4f.z3;
        multiplyMatrix.dy = this.x1 * matrix4f.dx + this.y1 * matrix4f.dy + this.z1 * matrix4f.dz + this.dy * matrix4f.ff;
        multiplyMatrix.x2 = this.x2 * matrix4f.x0 + this.y2 * matrix4f.x1 + this.z2 * matrix4f.x2 + this.dz * matrix4f.x3;
        multiplyMatrix.y2 = this.x2 * matrix4f.y0 + this.y2 * matrix4f.y1 + this.z2 * matrix4f.y2 + this.dz * matrix4f.y3;
        multiplyMatrix.z2 = this.x2 * matrix4f.z0 + this.y2 * matrix4f.z1 + this.z2 * matrix4f.z2 + this.dz * matrix4f.z3;
        multiplyMatrix.dz = this.x2 * matrix4f.dx + this.y2 * matrix4f.dy + this.z2 * matrix4f.dz + this.dz * matrix4f.ff;
        multiplyMatrix.x3 = this.x3 * matrix4f.x0 + this.y3 * matrix4f.x1 + this.z3 * matrix4f.x2 + this.ff * matrix4f.x3;
        multiplyMatrix.y3 = this.x3 * matrix4f.y0 + this.y3 * matrix4f.y1 + this.z3 * matrix4f.y2 + this.ff * matrix4f.y3;
        multiplyMatrix.z3 = this.x3 * matrix4f.z0 + this.y3 * matrix4f.z1 + this.z3 * matrix4f.z2 + this.ff * matrix4f.z3;
        multiplyMatrix.ff = this.x3 * matrix4f.dx + this.y3 * matrix4f.dy + this.z3 * matrix4f.dz + this.ff * matrix4f.ff;
        return multiplyMatrix;
    }

    public Matrix4f scale(float x, float y, float z) {
        Matrix4f scalar = new Matrix4f();
        scalar.x0 = x;
        scalar.y1 = y;
        scalar.z2 = z;
        return scalar;
    }

    public Matrix4f translate(float x, float y, float z) {
        Matrix4f translation = new Matrix4f();
        translation.dx = x;
        translation.dy = y;
        translation.dz = z;
        return translation;
    }

    public static Matrix4f rotate(float angle, float x, float y, float z) {
        Matrix4f rotation = new Matrix4f();
        float radians = (float) PI * angle / 180;
        float cos_a = (float) cos(radians);
        float sin_a = (float) sin(radians);
        rotation.x0 = x * x * (1f - cos_a) + cos_a;
        rotation.y0 = x * y * (1f - cos_a) - z * sin_a;
        rotation.z0 = x * z * (1f - cos_a) + y * sin_a;
        rotation.x1 = x * y * (1f - cos_a) + z * sin_a;
        rotation.y1 = y * y * (1f - cos_a) + cos_a;
        rotation.z1 = y * z * (1f - cos_a) - x * sin_a;
        rotation.x2 = x * z * (1f - cos_a) - y * sin_a;
        rotation.y2 = y * z * (1f - cos_a) + x * sin_a;
        rotation.z2 = z * z * (1f - cos_a) + cos_a;
        return rotation;
    }

    public void toBuffer(FloatBuffer buffer) {
        buffer.put(x0).put(y0).put(z0).put(dz);
        buffer.put(x1).put(y1).put(z1).put(dy);
        buffer.put(x2).put(y2).put(z2).put(dz);
        buffer.put(x3).put(y3).put(z3).put(ff);
        buffer.flip();
    }

    public FloatBuffer getBuffer() {
        try (MemoryStack stack = MemoryStack.stackPush()) {
            FloatBuffer buffer = stack.mallocFloat(4 * 4);
            toBuffer(buffer);
            return buffer;
        }
    }
}
