package seat;

import seat.type.Type;

public class Seat
{
    private final Type type;
    private final Integer row;
    private final Integer number;
    private boolean free;

    public Seat(Type type, Integer row, Integer number)
    {
        this.type = type;
        this.row = row;
        this.number = number;
        this.free = true;

    }

    public boolean book()
    {
        if (free)
        {
            System.out.println("The seat number " + number + " in row " + row + " has been booked");
            free = false;
            return true;
        }
        System.out.println("This place is unavailable");
        return false;
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

    public boolean isFree()
    {
        return free;
    }

    @Override
    public String toString()
    {
        return type.toString() + '|' + row + '|' + number + '|' + free;
    }
}
