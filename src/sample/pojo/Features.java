package sample.pojo;

public class Features {
    private Integer id;
    private String name;

    @Override
    public String toString() {
        return  name ;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Features(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Features(Integer id) {
        this.id = id;
    }
}
