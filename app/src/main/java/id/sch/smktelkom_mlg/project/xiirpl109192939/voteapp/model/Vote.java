package id.sch.smktelkom_mlg.project.xiirpl109192939.voteapp.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vergie on 18/11/16.
 */
public class Vote {
    String nama,startfrom;
    int durasi;
    Map<Integer,Candidates> cnddts = new HashMap<Integer, Candidates>();
    boolean needapprove,privat;

    public Vote(String nama, Integer durasi,boolean needapprove,boolean privat,String startfrom,Map<Integer,Candidates> cnddts) {
        this.nama = nama ;
        this.durasi = durasi;
        this.needapprove = needapprove;
        this.privat = privat;
        this.startfrom = startfrom;
        this.cnddts = cnddts;
    }

    public String getNama() {
        return nama;
    }

    public String getNamaCalon(int pos) {
        return this.cnddts.get(pos).toString();
    }
}
