package raytrace;

import java.util.List;
import java.util.ListIterator;

public class HitableList extends Hitable {
    List<Hitable> hitables;

    public HitableList(List<Hitable> hitables) {
        this.hitables = hitables;
    }

    public boolean hit(Ray r, double t_min, double t_max, HitRecord rec) {
        HitRecord temp_rec = new HitRecord();
        boolean hit_anything = false;

        ListIterator<Hitable> itr = this.hitables.listIterator();
        while (itr.hasNext()) {
            if (itr.next().hit(r, t_min, t_max, rec)) {
                hit_anything = true;
                rec = temp_rec;
            }
        }
        return hit_anything;
    }
}
