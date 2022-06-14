package aplikasi.rifqiarkan.xsportapp.model;

public class SportPlace {
    private String title, subTitle, location;

    public SportPlace(String title,String subTitle){
        this.title = title;
        this.subTitle = subTitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }
}
