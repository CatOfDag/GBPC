package com.huse.utils;

import com.huse.pojo.Score;
import com.huse.pojo.VoteResult;

public interface ScoreCountFuncation {
    //处理总分的方法
    static double ScoreHandle(VoteResult vtrs){
        //计算结果
        //德
        double vRes =(vtrs.getV1count()*100+vtrs.getV2count()*85+vtrs.getV3count()*70+vtrs.getV4count()*50)
                / (double)(vtrs.getV1count()+vtrs.getV2count()+vtrs.getV3count()+vtrs.getV4count());
        //绩
        double fRes =(vtrs.getF1count()*100+vtrs.getF2count()*85+vtrs.getF3count()*70+vtrs.getF4count()*50)
                / (double)(vtrs.getF1count()+vtrs.getF2count()+vtrs.getF3count()+vtrs.getF4count());
        //廉
        double hRes =(vtrs.getH1count()*100+vtrs.getH2count()*85+vtrs.getH3count()*70+vtrs.getH4count()*50)
                / (double)(vtrs.getH1count()+vtrs.getH2count()+vtrs.getH3count()+vtrs.getH4count());
        //勤
        double dRes =(vtrs.getD1count()*100+vtrs.getD2count()*85+vtrs.getD3count()*70+vtrs.getD4count()*50)
                / (double)(vtrs.getD1count()+vtrs.getD2count()+vtrs.getD3count()+vtrs.getD4count());
        //能
        double aRes =(vtrs.getA1count()*100+vtrs.getA2count()*85+vtrs.getA3count()*70+vtrs.getA4count()*50)
                / (double)(vtrs.getA1count()+vtrs.getA2count()+vtrs.getA3count()+vtrs.getA4count());
        System.out.println("v="+vRes+";f="+fRes+";h="+hRes+";d="+dRes+";a="+aRes);
        return (vRes*0.2+hRes*0.2+fRes*0.2+dRes*0.2+aRes*0.2);
    }

    static VoteResult ScoreHandleInit(Score score, VoteResult vtrs){
        vtrs.setName(score.getCadreName());
        vtrs.setVotealias(score.getAlias());
        switch (score.getAbility()){
            case 1:
                vtrs.setA1count(vtrs.getA1count()+1);
                break;
            case 2:
                vtrs.setA2count(vtrs.getA2count()+1);
                break;
            case 3:
                vtrs.setA3count(vtrs.getA3count()+1);
                break;
            case 4:
                vtrs.setA4count(vtrs.getA4count()+1);
                break;
        }
        switch (score.getHonest()){
            case 1:
                vtrs.setH1count(vtrs.getH1count()+1);
                break;
            case 2:
                vtrs.setH2count(vtrs.getH2count()+1);
                break;
            case 3:
                vtrs.setH3count(vtrs.getH3count()+1);
                break;
            case 4:
                vtrs.setH4count(vtrs.getH4count()+1);
                break;
        }
        switch (score.getFeats()){
            case 1:
                vtrs.setF1count(vtrs.getF1count()+1);
                break;
            case 2:
                vtrs.setF2count(vtrs.getF2count()+1);
                break;
            case 3:
                vtrs.setF3count(vtrs.getF3count()+1);
                break;
            case 4:
                vtrs.setF4count(vtrs.getF4count()+1);
                break;
        }
        switch (score.getVirtue()){
            case 1:
                vtrs.setV1count(vtrs.getV1count()+1);
                break;
            case 2:
                vtrs.setV2count(vtrs.getV2count()+1);
                break;
            case 3:
                vtrs.setV3count(vtrs.getV3count()+1);
                break;
            case 4:
                vtrs.setV4count(vtrs.getV4count()+1);
                break;
        }
        switch (score.getDiligence()){
            case 1:
                vtrs.setD1count(vtrs.getD1count()+1);
                break;
            case 2:
                vtrs.setD2count(vtrs.getD2count()+1);
                break;
            case 3:
                vtrs.setD3count(vtrs.getD3count()+1);
                break;
            case 4:
                vtrs.setD4count(vtrs.getD4count()+1);
                break;
        }
        return vtrs;
    }
}
