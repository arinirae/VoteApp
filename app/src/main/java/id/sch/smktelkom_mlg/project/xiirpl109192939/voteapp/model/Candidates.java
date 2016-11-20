package id.sch.smktelkom_mlg.project.xiirpl109192939.voteapp.model;

import android.graphics.drawable.Drawable;

/**
 * Created by vergie on 14/11/16.
 */
public class Candidates {
    public String  nama;
    public  String deskripsi;
    public String foto;

    public Candidates(String nama,String deskripsi,String foto){
        this.nama = nama;
        this.deskripsi = deskripsi;
        this.foto = foto;
    }


    public String getNama() {
        return nama;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public String getFoto() {
        return foto;
    }
}
