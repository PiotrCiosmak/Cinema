package seat.collection;

import seat.Seat;
import seat.type.Type;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class SeatCollection
{
    private Map<Integer, Integer> rowNumberToTheNumberOfSeats;

    private List<List<Seat>> seats;

    public SeatCollection()
    {
        seats = new ArrayList<>();
        rowNumberToTheNumberOfSeats = new HashMap<>();
        int numberOfRows = generateNumberOfRows();
        for (int i = 0; i < numberOfRows; ++i)
        {
            int numberOfSeatsInRow = generateNumberOfSeatsInRow();
            rowNumberToTheNumberOfSeats.put(i, numberOfSeatsInRow);
            List<Seat> rowOfSeats = new ArrayList<>();
            Type seatTypeAcrossTheRow = Type.generateType();
            for (int j = 0; j < numberOfSeatsInRow; j++)
            {
                rowOfSeats.add(new Seat(seatTypeAcrossTheRow, i , j ));
            }
            seats.add(rowOfSeats);
        }
    }

    private Integer generateNumberOfRows()
    {
        return ThreadLocalRandom.current().nextInt(14) + 8;
    }

    private Integer generateNumberOfSeatsInRow()
    {
        return ThreadLocalRandom.current().nextInt(5) + 5;
    }

    public void show()
    {
        showSeatsNumbers();
        for (int i = 1; i < seats.size(); ++i) //TODO SPRAWDZIĆ WARUNKI GRANICZNE
        {
            showRowNumber(i);
            for (Seat seat : seats.get(i))
            {
                showSeat(seat);
            }
            System.out.println();
        }
    }

    private void showSeatsNumbers()
    {
        System.out.print("   |");
        Integer largestNumberOfSeatsInRow = getLargestNumberOfSeatsInRow();
        for (int i = 1; i <= largestNumberOfSeatsInRow; i++)
        {
            System.out.print(i);
        }
        System.out.println();
    }

    private Integer getLargestNumberOfSeatsInRow()
    {
        return rowNumberToTheNumberOfSeats.values().stream().mapToInt(Integer::intValue).max().orElse(Integer.MIN_VALUE);
    }

    private void showRowNumber(int rowNumber)
    {
        String formattedRowNumber = String.format("R%02d|", rowNumber);
        System.out.print(formattedRowNumber);
    }

    private void showSeat(Seat seat)
    {
        if (seat.isFree())
        {
            System.out.print('O');
        }
        else
        {
            System.out.print('X');
        }
    }

    public Map<Integer, Integer> getRowNumberToTheNumberOfSeats()
    {
        return rowNumberToTheNumberOfSeats;
    }

    public SeatCollection get()
    {
        return this;
    }

    public Optional<Seat> getSeatByRowNumberAndSeatNumber(Integer rowNumber, Integer seatNumber)
    {
        if (rowNumberIsCorrect(rowNumber) && seatNumberIsCorrect(rowNumber, seatNumber))
        {
            return Optional.of(seats.get(rowNumber).get(seatNumber));
        }
        return Optional.empty();
    }

    private boolean rowNumberIsCorrect(Integer rowNumber)
    {
        return rowNumber >= 0 && rowNumber < seats.size();//TODO SPRAWDZIĆ WARUNKI GRANICZNE W TEŚCIE
    }

    private boolean seatNumberIsCorrect(Integer rowNumber, Integer seatNumber)
    {
        return seatNumber >= 0 && seatNumber < seats.get(rowNumber).size();//TODO SPRAWDZIĆ WARUNKI GRANICZNE W TEŚCIE
    }
}
