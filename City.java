/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;

/**
 *
 * @author bindo
 */
public class City {
    Location current = null;
    ArrayList<Location> _locate = new ArrayList<Location>();
    
    public void addToList(Location locate){
        _locate.add(locate);
    }
    
    public void connectCity(){
        for(int i = 0; i<_locate.size(); i++){
            for(int j = 0; j<_locate.size(); j++){
                if(_locate.get(i).getLeftLocationName().equals(_locate.get(j).getCurrentLocation())){
                    _locate.get(i).setLocationLeft(_locate.get(j));
                }else if(_locate.get(i).getRightLocationName().equals(_locate.get(j).getCurrentLocation()))
                    _locate.get(i).setLocationRight(_locate.get(j));
            }
        }
        if(isAllConnected()){
            for(int i = 0; i<_locate.size(); i++){
                if(_locate.get(i).getRightLocationName().equals("Outside City")){
                    _locate.get(i).setLocationRight(new Location("Outside City","","Fourth Ave","Philadelphia","Philadelphia"));
                }else if(_locate.get(i).getLeftLocationName().equals("Outside City")){
                    _locate.get(i).setLocationLeft(new Location("Outside City","Fifth Ave","","Cleveland","Cleveland"));
                }
            }
        }
    }
    
    public boolean isAllConnected(){
        for(int i =0; i<_locate.size(); i++){
            if(_locate.get(i).getLeftLocation() == null && _locate.get(i).getRightLocation() == null)
                return false;
        }
        return true;
    }
    
    public boolean setStartingLocation(int i){
	
        switch(i){
            case 0: current = _locate.get(0);
                    break;
            case 1: current = _locate.get(1);
                    break;
            case 2: current = _locate.get(2);
                    break;
            case 3: current = _locate.get(3);
                    break;
            default: return false;
        }
        
        return true;
    }
    
    public String goRight(){
        String travel = "heading from " + current.getCurrentLocation() + " to " + current.getRightLocationName() + " via " + current.getRightStreet();
        current = current.getRightLocation();
        return travel;
    }
    
    public String goLeft(){
        String travel = "heading from " + current.getCurrentLocation() + " to " + current.getLeftLocationName() + " via " + current.getLeftStreet();
        current = current.getLeftLocation();
        return travel;
    }
    
    public String getCurrentLocation(){
        return current.getCurrentLocation();
    }
    
    public String getOutsideCityName(){
        return current.getLeftLocationName();
    }
        
}
