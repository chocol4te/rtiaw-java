package raytrace;

import static java.lang.Math.sqrt;

public class Vec3 {
    double a;
    double b;
    double c;

    public Vec3(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    // Coordinate
    public double x() {
        return this.a;
    }

    public double y() {
        return this.b;
    }

    public double z() {
        return this.c;
    }

    public double squared_length() {
        return (this.a * this.a) + (this.b * this.b) + (this.c * this.c);
    }

    public double length() {
        return sqrt(this.squared_length());
    }

    public Vec3 unit_vector() {
        return this.div(this.length());
    }

    public void make_unit_vector() {
        Vec3 unit = this.unit_vector();
        this.a = unit.a;
        this.b = unit.b;
        this.c = unit.c;
    }

    // Color
    public double r() {
        return this.a;
    }

    public double g() {
        return this.b;
    }

    public double b() {
        return this.c;
    }

    // Operations (no operator overloading in Java)
    public Vec3 add(Vec3 rhs) {
        return new Vec3(this.a + rhs.a, this.b + rhs.b, this.c + rhs.c);
    }

    public Vec3 add(double t) {
        return new Vec3(this.a + t, this.b + t, this.c + t);
    }

    public Vec3 sub(Vec3 rhs) {
        return new Vec3(this.a - rhs.a, this.b - rhs.b, this.c - rhs.c);
    }

    public Vec3 mul(Vec3 rhs) {
        return new Vec3(this.a * rhs.a, this.b * rhs.b, this.c * rhs.c);
    }

    public Vec3 mul(double t) {
        return new Vec3(this.a * t, this.b * t, this.c * t);
    }

    public Vec3 div(Vec3 rhs) {
        return new Vec3(this.a / rhs.a, this.b / rhs.b, this.c / rhs.c);
    }

    public Vec3 div(double t) {
        return new Vec3(this.a / t, this.b / t, this.c / t);
    }

    public double dot(Vec3 rhs) {
        return this.a * rhs.a + this.b * rhs.b + this.c * rhs.c;
    }

    public Vec3 cross(Vec3 rhs) {
        return new Vec3(this.b * rhs.c - this.c * rhs.b, -(this.a * rhs.c - this.c * rhs.a),
                this.a * rhs.b - this.b * rhs.a);
    }

    // `impl fmt::Debug {}` in Rust is nicer...
    @Override
    public String toString() {
        return "[" + this.a + "," + this.b + "," + this.c + "]";
    }
}
