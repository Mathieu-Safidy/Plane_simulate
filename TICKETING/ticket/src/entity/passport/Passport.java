package entity.passport;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import utility.Entity;
import utility.annotation.Column;
import utility.annotation.Ignore;
import utility.annotation.Table;

@Table(name = "passport")
public class Passport extends Entity {

    @Column(name = "id_passport")
    String id_passport;

    @Column(name = "id_users")
    String id_users;

    @Column(name = "image_passport")
    String passPhoto;

    @Ignore
    String fileName;

    @Ignore
    String directory;

    public Passport() throws Exception {
        this.initiate();
    }

    public Passport(String id_users, String passPhoto) throws Exception {
        this.passPhoto = passPhoto;
        this.id_users = id_users;
        this.initiate();
    }

    public Passport(String id_passport, String id_users, String passPhoto) throws Exception {
        this.id_passport = id_passport;
        this.passPhoto = passPhoto;
        this.id_users = id_users;
        this.initiate();
    }

    public void setId_passport(String id_passport) {
        this.id_passport = id_passport;
    }

    public void setPassPhoto(String passPhoto) {
        this.passPhoto = passPhoto;
    }

    public String getId_passport() {
        return this.id_passport;
    }

    public String getPassPhoto() {
        return this.passPhoto;
    }

    public String getFileName() {
        return fileName;
    }

    // public void setFileName(String fileName) {
    //     this.fileName = fileName;
    // }

    public void setFileName(String filename) {
        UUID uuid = UUID.randomUUID();
        System.out.println("filename "+filename);
        String extension = Passport.getExtension(filename);
        if (directory == null || directory.isEmpty()) {
            this.directory = "/";
        }
        System.out.println("filenme final "+directory + File.separator + uuid.toString() + "." +extension);

        this.fileName = (directory + File.separator + uuid.toString() + "." +extension);
        System.out.println("insert filename "+this.fileName);

        this.passPhoto = this.fileName;
    }
    

    public static String getExtension(String fileName) {
        int lastDot = fileName.lastIndexOf('.');
        if (lastDot == -1 || lastDot == fileName.length() - 1) {
            return ""; // pas d’extension trouvée
        }
        return fileName.substring(lastDot + 1);
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public String getDirectory() {
        return this.directory;
    }

    public String getId_users() {
        return id_users;
    }

    public void setId_users(String id_users) {
        this.id_users = id_users;
    }

    public List<Passport> convertToPassport(Object[] obj) throws Exception {
        List<Passport> prods = new ArrayList<>();
        for (Object prod : obj) {
            Passport product = (Passport) prod;
            // product.manyToOne();
            prods.add(product);
        }
        return prods;
    }

}