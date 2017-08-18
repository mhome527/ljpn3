package vn.sjpn3.jlptn3.entity;


/**
 * Created by HuynhTD on 12/23/2016.
 */

public class GrammarDetailEntity {
    public String jp;

    public String romaji;

    public String ot;

    public GrammarDetailEntity() {
    }

    public GrammarDetailEntity(String jp, String romaji, String ot) {
        this.jp = jp;
        this.romaji = romaji;
        this.ot = ot;
    }

    public String getJp() {
        return jp;
    }

    public void setJp(String jp) {
        this.jp = jp;
    }

    public String getRomaji() {
        return romaji;
    }

    public void setRomaji(String romaji) {
        this.romaji = romaji;
    }

    public String getOt() {
        return ot;
    }

    public void setOt(String ot) {
        this.ot = ot;
    }
}
