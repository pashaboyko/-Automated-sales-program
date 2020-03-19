package sample.pojo;
public class Date
{
    private int day;
    private int month;
    private int year;
    public Date (int d, int m, int y)
    {
        this.day=d;
        this.month=m;
        this.year=y;
    }
    public Date ()
    {
        day=month=year=0;
    }
    public Date (Date another)
    {
        this.day=another.day;
        this.month=another.month;
        this.year=another.year;
    }

    public String strd() {
        return new String(day+"."+month+"."+year);
    }

    public void print (){
        System.out.println(day+"."+month+"."+year);
    }
    public int getDay ()
    {
        return day;
    }
    public void setDay (int d)
    {
        this.day=d;
    }

    public int getMonth ()
    {
        return month;
    }
    public void setMonth (int m)
    {
        this.month=m;
    }

    public int getYear ()
    {
        return year;
    }
    public void setYear (int y)
    {
        this.year=y;
    }
}