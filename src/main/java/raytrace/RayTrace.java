package raytrace;

import static java.lang.Math.sqrt;
import java.io.FileWriter;
import java.util.*;

public class RayTrace {
    public static void main(String[] args) {
        final int nx = 400;
        final int ny = 200;
        final int ns = 100;

        final Camera cam = new Camera();

        List<Hitable> list = new ArrayList<Hitable>();
        list.add(new Sphere(new Vec3(0, 0, -1), 0.5));
        list.add(new Sphere(new Vec3(0, -100.5, -1), 100));
        HitableList world = new HitableList(list);

        try {
            FileWriter fw = new FileWriter("out.ppm");
            fw.write("P3\n");
            fw.write(String.format("%s %s\n", nx, ny));
            fw.write("255\n");

            for (int j = ny - 1; j >= 0; j--) {
                for (int i = 0; i < nx; i++) {
                    Vec3 col = new Vec3(0, 0, 0);
                    Random rand = new Random();
                    for (int s = 0; s < ns; s++) {
                        double u = ((double) i + rand.nextDouble()) / (double) nx;
                        double v = ((double) j + rand.nextDouble()) / (double) ny;

                        col = col.add(cam.get_ray(u, v).color(world));
                    }

                    Vec3 color = col.div((double) ns);

                    color = new Vec3(sqrt(color.r()), sqrt(color.g()), sqrt(color.b())).mul(255);
                    int ir = (int) Math.round(color.r());
                    int ig = (int) Math.round(color.g());
                    int ib = (int) Math.round(color.b());

                    fw.write(String.format("%d %d %d\n", ir, ig, ib));
                }
            }

            fw.close();
        } catch (Exception e) {
            // Only possible exceptions are file IO
            System.out.print("Writing out image failed with exception: ");
            System.out.println(e);
        }
    }
}
