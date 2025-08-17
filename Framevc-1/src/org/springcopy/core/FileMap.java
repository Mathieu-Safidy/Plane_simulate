package org.springcopy.core;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileMap {

    String filename;
    byte[] filebody;

    public FileMap(String name, byte[] body) {
        this.setFilebody(body);
        this.setFilename(name);
    }

    public void setFilename(String name) {
        this.filename = name;
    }

    public void setFilebody(byte[] body) {
        this.filebody = body;
    }

    public String getFilename() {
        return filename;
    }

    public byte[] getFilebody() {
        return filebody;
    }

    public int ecritureFichier(String filePath) {
        try (FileOutputStream fos = new FileOutputStream(filePath + File.separatorChar + filename)) {
            fos.write(this.filebody);
            System.out.println("Fichier écrit avec succès !");
            return 1;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
