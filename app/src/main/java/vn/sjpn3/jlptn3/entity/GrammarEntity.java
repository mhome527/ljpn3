package vn.sjpn3.jlptn3.entity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import vn.sjpn3.jlptn3.utils.Log;

/**
 * Created by HuynhTD on 10/17/2016.
 */

public class GrammarEntity {
    private int num;
    private int level;
    private String jp;
    private String romaji;
    private String mean;
    private String formation;
    private String example;
    List<GrammarDetailEntity> details;

    public GrammarEntity() {

    }

    ///////////
    public void formatData() {
        Gson gson = new Gson();
        Log.i("GrammarEntity", "formatData json:" + example);
        details = gson.fromJson(example, new TypeToken<List<GrammarDetailEntity>>() {
        }.getType());
        if (details != null)
            Log.i("GrammarEntity", "formatData size: " + details.size() + "; item jp:" + details.get(0).getJp());
        else
            details = new ArrayList<>();
    }

    public List<GrammarDetailEntity> getDetails() {
        return details;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getMean() {
        return mean;
    }

    public void setMean(String mean) {
        this.mean = mean;
    }

    public String getFormation() {
        return formation;
    }

    public void setFormation(String formation) {
        this.formation = formation;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
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


}
