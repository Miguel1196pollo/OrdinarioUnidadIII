package mx.edu.utng.orders.model;

/**
 * Created by Toshiba on 23/02/2017.
 */
public class Platform {
    private String idPlatform;
    private String imgFilename;
    private String name;

    public Platform(String idPlatform, String imgFilename, String name) {
        this.idPlatform = idPlatform;
        this.imgFilename = imgFilename;
        this.name = name;
    }

    public Platform() {
        this("","","");
    }

    public String getIdPlatform() {
        return idPlatform;
    }

    public void setIdPlatform(String idPlatform) {
        this.idPlatform = idPlatform;
    }

    public String getImgFilename() {
        return imgFilename;
    }

    public void setImgFilename(String imgFilename) {
        this.imgFilename = imgFilename;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Platform{" +
                "idPlatform='" + idPlatform + '\'' +
                ", imgFilename='" + imgFilename + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
