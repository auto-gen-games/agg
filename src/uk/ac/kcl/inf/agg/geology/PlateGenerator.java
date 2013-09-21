package uk.ac.kcl.inf.agg.geology;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class PlateGenerator {
    private static boolean covered (List<Plate> plates, Position position) {
        for (Plate plate : plates) {
            if (plate.covers (position)) {
                return true;
            }
        }
        return false;
    }

    public static List<Plate> generatePlates (ShallowWorld world, int plateLayer, int numberOfPlates) {
        List<Plate> plates = new LinkedList<> ();
        Random random = new Random ();
        List<Position> expansions;
        Position position;
        boolean expanded;

        for (int count = 0; count < numberOfPlates; count += 1) {
            do {
                position = world.randomPosition ().movedToZ (plateLayer);
            } while (covered (plates, position));
            plates.add (new Plate (position));
        }
        do {
            expanded = false;
            for (Plate plate : plates) {
                expansions = getExpansions (world, plates, plate);
                if (expansions.size () > 0) {
                    expanded = true;
                    position = expansions.get (random.nextInt (expansions.size ()));
                    plate.add (position);
                }
            }
        } while (expanded);
        
        return plates;
    }

    private static List<Position> getExpansions (ShallowWorld world, List<Plate> plates, Plate forPlate) {
        List<Position> expansions = new LinkedList<> ();
        Position west, east, north, south;

        try {
            for (Position position : forPlate.getCoverage ()) {
                west = world.getRelative (position, -1, 0, 0);
                east = world.getRelative (position, 1, 0, 0);
                north = world.getRelative (position, 0, -1, 0);
                south = world.getRelative (position, 0, 1, 0);
                expansions.addAll (getNotCovered (plates, west, east, north, south));
            }

            return expansions;
        } catch (InvalidPositionException shouldNotOccur) {
            throw new RuntimeException (shouldNotOccur);
        }
    }

    private static List<Position> getNotCovered (List<Plate> plates, Position... positions) {
        List<Position> notCovered = new LinkedList<> ();

        for (Position position : positions) {
            if (!covered (plates, position)) {
                notCovered.add (position);
            }
        }

        return notCovered;
    }
}
