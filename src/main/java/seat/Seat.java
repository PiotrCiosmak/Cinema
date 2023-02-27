package seat;

import seat.type.Type;

public class Seat
{
    private Type type;
    private Integer row;
    private Integer number;

    public Seat(Type genre, Integer row, Integer number)
    {
        this.type = genre;
        this.row = row;
        this.number = number;
    }

    public Type getType()
    {
        return type;
    }

    public Integer getRow()
    {
        return row;
    }

    public Integer getNumber()
    {
        return number;
    }

    @Override
    public String toString()
    {
        return type.toString() + '|' + row + '|' + number;
    }
}
