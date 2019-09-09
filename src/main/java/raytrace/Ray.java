package raytrace;

import static java.lang.Math.sqrt;

import java.util.Random;

public class Ray {
    Vec3 a;
    Vec3 b;

    public Ray(Vec3 a, Vec3 b) {
        this.a = a;
        this.b = b;
    }

    public Vec3 origin() {
        return this.a;
    }

    public Vec3 direction() {
        return this.b;
    }

    public Vec3 point_at_parameter(double t) {
        return this.a.add(this.b.mul(t));
    }

    public Vec3 color(Hitable world) {
        HitRecord rec = new HitRecord();

        if (world.hit(this, 0.001, Double.MAX_VALUE, rec)) {
            Vec3 random_in_unit = new Vec3(0, 0, 0);
            Random rand = new Random();
            do {
                random_in_unit = new Vec3(rand.nextDouble(), rand.nextDouble(), rand.nextDouble()).mul(2)
                        .sub(new Vec3(1, 1, 1));
            } while (random_in_unit.squared_length() >= 1.0);

            Vec3 target = rec.p.add(rec.normal).add(random_in_unit);
            return new Ray(rec.p, target.sub(rec.p)).color(world).mul(0.5);
        } else {
            Vec3 unit_direction = this.direction().unit_vector();
            double t = 0.5 * (unit_direction.y() + 1.0);
            return new Vec3(1, 1, 1).mul(1.0 - t).add((new Vec3(0.5, 0.7, 1.0).mul(t)));
        }
    }

    public double hit_sphere(Vec3 center, double radius) {
        Vec3 oc = this.origin().sub(center);
        double a = this.direction().dot(this.direction());
        double b = 2.0 * oc.dot(this.direction());
        double c = oc.dot(oc) - radius * radius;
        double discriminant = b * b - 4 * a * c;

        if (discriminant < 0) {
            return -1.0;
        } else {
            return (-b - sqrt(discriminant)) / (2.0 * a);
        }
    }
}
