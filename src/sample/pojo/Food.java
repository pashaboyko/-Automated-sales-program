package sample.pojo;

import sample.pojo.Product;
import sample.pojo.Date;


public class Food  extends Product
{
    private Date date;
    public Food (int id, String n, String bar, double pr,String photo, int day, int month, int year)
    {
        super(id,n,bar,pr,photo);
        date=new Date(day,month,year);
    }
    public Food(int anInt, String string, String rsString, double aDouble, String s, Date date)
    {
        super();
        this.date=date;  //вообще не уверен, будет ли этот рядок работать
    }
    public Food(Food another)
    {
        super(another);
        this.date=another.date;
    }
    public Date getDate()
    {
        return date;
    }
    public void setDate(Date d)
    {
        this.date=d;
    }

}