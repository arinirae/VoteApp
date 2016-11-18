package id.sch.smktelkom_mlg.project.xiirpl109192939.voteapp.model;

/**
 * Created by vergie on 18/11/16.
 */
public class Vote {
    String nama,startfrom;
    int durasi;
    UserVote usrvote;
    boolean needapprove,privat;

    public Vote(String nama, Integer durasi,boolean needapprove,boolean privat,String startfrom,UserVote usrvote) {
        this.nama = nama ;
        this.durasi = durasi;
        this.needapprove = needapprove;
        this.privat = privat;
        this.startfrom = startfrom;
        this.usrvote = usrvote;
    }
}
