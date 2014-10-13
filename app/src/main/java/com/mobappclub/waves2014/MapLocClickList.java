package com.mobappclub.waves2014;

import java.util.ArrayList;
import java.util.List;
import android.app.ListActivity;
import android.os.Bundle;

public class MapLocClickList extends ListActivity {

	String location ; 
	
	ArrayList<String> name ; 
	ArrayList<String> time ; 
	ArrayList<String> type ; 
	ArrayList<String> body ; 
	
	
	String[] name1 ; 
	String[] time1 ; 
	String[] type1 ; 
	String[] body1 ; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 
		setContentView(R.layout.fragment_updates);
		//Get location from earlier activity
		Bundle b = getIntent().getExtras();
		location = b.getString("Location");
		int id = b.getInt("Id");
		
		DatabaseHandler db = new DatabaseHandler(getApplicationContext());
		
		name = new ArrayList<String>();
	    time = new ArrayList<String>();
	    type = new ArrayList<String>();
	    body = new ArrayList<String>();
	    
		
		List<EventsObject> events  = db.getAllEvents();

		
		if(id == 1){
			//Add events to location list 
	        for (EventsObject event : events ) {
	        	
	        	if(event.getLocation().startsWith("A-")){
	        		
	        		//Show events on that location
	        		name.add(event.getName());
	        		time.add(event.getStart_time());
	        		type.add(event.getType());
	        		body.add(event.getLocation());
	        		
	        		} 
	        	}
	        

	        	
	        	
	        	

			}
		
		else if(id == 2){
			//Add events to location list 
	        for (EventsObject event : events ) {
	        	
	        	if(event.getLocation().startsWith("C-")){
	        		
	        		//Show events on that location
	        		name.add(event.getName());
	        		time.add(event.getStart_time());
	        		type.add(event.getType());
	        		body.add(event.getLocation());
	        		
	        		} 
	        	}

		}
		
		
		else{
			
		//Add events to location list 
        for (EventsObject event : events ) {
        	
        	if(event.getLocation().equalsIgnoreCase(location)){
        		
        		//Show events on that location
        		name.add(event.getName());
        		time.add(event.getStart_time());
        		type.add(event.getType());
        		body.add(event.getLocation());
        		
        		} 
        	}
        

        	
        	
        	

		}
        
        name1 = new String[name.size()];
	    time1 = new String[name.size()];
	    type1 = new String[name.size()];
	    body1 = new String[name.size()];
	    
        int j = 0 ;
        for(String n : name ){
        	
        	name1[j] = n ;
        	j++ ; 
        }
        
        int k = 0 ;
        for(String t : time ){
        	
        	time1[k] = t ;
        	k++ ; 
        }
        
        int l = 0 ;
        for(String ty : type ){
        	
        	type1[l] = ty ;
        	l++ ; 
        }
        
        int m = 0 ;
        for(String bo : body ){
        	
        	body1[m] = bo ;
        	m++ ; 
        }
        	
        


        }
	
	
		
		
	

}
