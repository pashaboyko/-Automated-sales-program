package sample.pojo;



public class Clothes extends Product
{
    private int size;
    private String color;
    private String season;
    public Clothes (int id, String n, String bar, double pr,String photo, int s, String col, String seas)
    {
        super(id,n,bar,pr,photo);
        this.size=s;
        this.color=col;
        this.season=seas;
    }
    public Clothes()
    {
        super();
    }
    public Clothes(Clothes another)
    {
        super(another);
        this.size=another.size;
        this.color=another.color;
        this.season=another.season;
    }
    public int getSize()
    {
        return size;
    }
    public void setSize(int s)
    {
        this.size=s;
    }
    public String getColor()
    {
        return color;
    }
    public void setColor(String c)
    {
        this.color=c;
    }
    public String  getSeason()
    {
        return season;
    }
    public void setSeason (String  s)
    {
        this.season=s;
    }

}