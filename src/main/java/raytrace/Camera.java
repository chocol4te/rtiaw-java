package raytrace;

public class Camera {
    Vec3 origin;
    Vec3 lower_left_corner;
    Vec3 horizontal;
    Vec3 vertical;

    public Camera() {
        this.origin = new Vec3(0.0, 0.0, 0.0);
        this.lower_left_corner = new Vec3(-2.0, -1.0, -1.0);
        this.horizontal = new Vec3(4.0, 0.0, 0.0);
        this.vertical = new Vec3(0.0, 2.0, 0.0);
    }

    public Ray get_ray(double u, double v) {
        return new Ray(origin, lower_left_corner.add(horizontal.mul(u)).add(vertical.mul(v)));
    }
}
