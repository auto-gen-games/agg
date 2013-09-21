package uk.ac.kcl.inf.agg.geology;

public class BaseCellGenerator implements CellGenerator {
    private final int _seaLevel, _seaBed;

    public BaseCellGenerator (int seaLevel, int seaBed) {
        _seaLevel = seaLevel;
        _seaBed = seaBed;
    }
    
    @Override
    public Cell createCell (Position position) {
        if (position.z <= _seaBed) {
            return Cell.rock;
        }
        if (position.z <= _seaLevel) {
            return Cell.water;
        }
        return Cell.air;
    }

}
