package id.sch.smktelkom_mlg.project.xiirpl109192939.voteapp.model;

import java.util.Map;

/**
 * Created by Kasir 2 (cadangan) on 11/21/2016.
 */
public class VoteData {
    String nama, startfrom;
    int durasi;
    boolean needapprove, privat;
    Map<String,String> pilihan,uservote;
    public VoteData(String nama, Integer durasi, boolean needapprove, boolean privat, String startfrom, Map<String,String> pilihan, Map<String,String> uservote) {
        this.nama = nama;
        this.durasi = durasi;
        this.needapprove = needapprove;
        this.privat = privat;
        this.startfrom = startfrom;
        this.pilihan = pilihan;
        this.uservote = uservote;
    }
}