package raytrace;

import static java.lang.Math.sqrt;

public class Sphere extends Hitable {
    public Vec3 centre;
    public double radius;

    public Sphere(Vec3 centre, double radius) {
        this.centre = centre;
        this.radius = radius;
    }

    @Override
    public boolean hit(Ray r, double t_min, double t_max, HitRecord rec) {
        Vec3 oc = r.origin().sub(this.centre);
        double a = r.direction().dot(r.direction());
        double b = oc.dot(r.direction());
        double c = oc.dot(oc) - radius * radius;

        double discriminant = b * b - a * c;

        if (discriminant > 0) {
            double temp = (-b - sqrt(discriminant)) / a;
            if (temp < t_max && temp > t_min) {
                rec.t = temp;
                rec.p = r.point_at_parameter(rec.t);
                rec.normal = (rec.p.sub(this.centre)).div(radius);
                return true;
            }

            temp = (-b + sqrt(discriminant)) / a;
            if (temp < t_max && temp > t_min) {
                rec.t = temp;
                rec.p = r.point_at_parameter(rec.t);
                rec.normal = (rec.p.sub(this.centre)).div(radius);
                return true;
            }
        }

        return false;
    }
}
