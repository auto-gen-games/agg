package uk.ac.kcl.inf.agg.geology;

import java.util.List;

public class Test {
    public static void main (String[] arguments) {
        int width = 20, breadth = 20, depth = 20, seaBed = 5, seaLevel = 10, numPlates = 4;
        CellGenerator generator = new BaseCellGenerator (seaLevel, seaBed);
        ShallowWorld world = new ShallowWorld (width, breadth, depth, generator);
        List<Plate> plates = PlateGenerator.generatePlates (world, seaBed, numPlates);
        Position position;

        for (int y = 0; y < breadth; y += 1) {
            for (int x = 0; x < width; x += 1) {
                position = new Position (x, y, seaBed);
                for (int index = 0; index < numPlates; index += 1) {
                    if (plates.get (index).covers (position)) {
                        System.out.print (index);
                        break;
                    }
                }
            }
            System.out.println ();
        }
    }
}
