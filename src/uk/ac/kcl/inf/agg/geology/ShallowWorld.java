package uk.ac.kcl.inf.agg.geology;

import java.util.Random;

public class ShallowWorld {
    private final Cell[][][] _cells;
    private final CellGenerator _generator;
    private final Random _random;

    public ShallowWorld (int width, int breadth, int depth, CellGenerator generator) {
        _cells = new Cell[width][breadth][depth];
        _generator = generator;
        _random = new Random ();
    }

    private void check (Position position) throws InvalidPositionException {
        if (position.x < 0 || position.x > _cells.length
            || position.y < 0 || position.y > _cells[0].length
            || position.z < 0 || position.z > _cells[0][0].length) {
            throw new InvalidPositionException ();
        }
    }

    public int getBreadth () {
        return _cells[0].length;
    }

    public Cell getCell (Position position) throws InvalidPositionException {
        Cell cell;

        check (position);
        cell = _cells[position.x][position.y][position.z];
        if (cell == null) {
            cell = _generator.createCell (position);
            _cells[position.x][position.y][position.z] = cell;
        }

        return cell;
    }

    public int getDepth () {
        return _cells[0][0].length;
    }

    public Position getRelative (Position start, int dx, int dy, int dz) throws InvalidPositionException {
        int x = start.x + dx;
        int y = start.y + dy;
        int z = start.z + dz;
        Position result;
        
        while (x < 0) {
            x += _cells.length;
        }
        while (x >= _cells.length) {
            x -= _cells.length;
        }
        while (y < 0) {
            y += _cells[0].length;
        }
        while (y >= _cells[0].length) {
            y -= _cells[0].length;
        }
        result = new Position (x, y, z);
        check (result);
        
        return result;
    }

    public int getWidth () {
        return _cells.length;
    }
    
    public Position randomPosition () {
        int x = _random.nextInt (getWidth ());
        int y = _random.nextInt (getBreadth ());
        int z = _random.nextInt (getDepth ());
        
        return new Position (x, y, z);
    }
    
    public void setCell (Position position, Cell newCell) throws InvalidPositionException {
        check (position);
        _cells[position.x][position.y][position.z] = newCell;
    }
}
