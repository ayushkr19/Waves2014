package com.mobappclub.waves2014;

/**
 * Created by Akshay on 12-10-2014.
 */
public class SponsorObject {	//private variables
    int id;
    String name_of_sponsor;
    String img_url_link;
    String link ;

    // Empty constructor
    public SponsorObject() {
    }

    public SponsorObject(int id, String name , String url , String link){
        this.id = id;
        this.name_of_sponsor = name;
        this.img_url_link = url;
        this.link = link ;

    }

    public SponsorObject(String name , String url , String link ){

        this.name_of_sponsor = name;
        this.img_url_link = url;
        this.link = link ;

    }

    // getting ID
    public int getID(){
        return this.id;
    }

    // setting id
    public void setID(int id){
        this.id = id;
    }

    // getting name of sponsor
    public String getNameofSponsor(){
        return this.name_of_sponsor;
    }

    // setting name
    public void setName(String name){
        this.name_of_sponsor = name;
    }

    // getting Link to image
    public String getImgUrl(){
        return this.img_url_link;
    }

    // setting Link to image
    public void setImgUrl(String url){
        this.img_url_link = url;
    }

    // getting Link of sponsors page
    public String getLink(){
        return this.link;
    }

    // setting Link  sponsors page
    public void setLink(String link){
        this.link = link;
    }


}
