package com.mobappclub.waves2014;

public class UpdateObject {
	int id;
	String type ; 
	String event_id ; 
    String timestamp ; 
    String update_head ; 
    String update_body ;
        
    public UpdateObject(){
    	
    }
    public UpdateObject(int id , String type ,String event_id , String timestamp , String update_head , String update_body){
    	
    	this.id = id;
    	this.type = type ; 
    	this.event_id = event_id;
    	this.timestamp = timestamp ; 
    	this.update_head = update_head ; 
    	this.update_body = update_body ; 
    	
    }
    
    public UpdateObject( String type ,String event_id , String timestamp , String update_head , String update_body){
    	
    	this.type = type ; 
    	this.event_id = event_id;
    	this.timestamp = timestamp ; 
    	this.update_head = update_head ; 
    	this.update_body = update_body ; 
    	
    }
    
    // getting ID
    public int getID(){
        return this.id;
    }
     
    // setting id
    public void setID(int id){
        this.id = id;
    }
    
 // getting Type
    public String getType(){
        return this.type;
    }
     
    // setting Event id
    public void setType(String Type){
        this.type = Type;
    }
    
    // getting Event ID
    public String getEvent_Id(){
        return this.event_id;
    }
     
    // setting Event id
    public void setEvent_Id(String event_id){
        this.event_id = event_id;
    }
    
    // getting TimeStamp
    public String getTimeStamp(){
        return this.timestamp;
    }
     
    // setting TimeStamp
    public void setTimeStamp(String TimeStamp){
        this.timestamp = TimeStamp;
    }
    
    
    
    // getting Head
    public String getHead(){
        return this.update_head;
    }
     
    // setting Head
    public void setHead(String update_head){
        this.update_head = update_head;
    }
    
    // getting Body
    public String getBody(){
        return this.update_body;
    }
     
    // setting Event id
    public void setBody(String update_body){
        this.update_body = update_body;
    }
 
}
