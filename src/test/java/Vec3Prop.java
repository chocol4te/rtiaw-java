import raytrace.Vec3;
import org.junit.jupiter.api.Test;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(JUnitQuickcheck.class)
public class Vec3Prop {
    @Property
    public void equivalent_accessors(double a, double b, double c) {
        Vec3 v = new Vec3(a, b, c);

        assertEquals(a, v.x(), 0);
        assertEquals(b, v.y(), 0);
        assertEquals(c, v.z(), 0);

        assertEquals(a, v.r(), 0);
        assertEquals(b, v.g(), 0);
        assertEquals(c, v.b(), 0);
    }

    @Property
    public void unit_vector(double a, double b, double c) {
        Vec3 v = new Vec3(a, b, c);

        v.make_unit_vector();

        assertEquals(1, v.length(), 0.1);
    }

    @Property
    public void mul_double(double a, double b, double c, double t) {
        Vec3 v = new Vec3(a, b, c);

        v.mul(t);

        assertEquals(v.x(), a * t, 0);
        assertEquals(v.y(), b * t, 0);
        assertEquals(v.z(), c * t, 0);
    }

    @Test
    void simple_length() {
        Vec3 v = new Vec3(1, 2, 2);
        assertEquals(3, v.length(), 0);
    }
}
