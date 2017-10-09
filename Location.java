/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author bindo
 */
public class Location {
    Location leftLocation;
    Location rightLocation;
    String currentLocationName;
    String leftLocationName;
    String rightLocationName;
    String leftStreet;
    String rightStreet;
    String outsideCityName=null;
    
    public Location(String currentLocationName, String leftStreet, String rightStreet, String leftLocationName, String rightLocationName){
        this.currentLocationName = currentLocationName;
        this.leftStreet = leftStreet;
        this.rightStreet = rightStreet;
        this.outsideCityName = "";
        this.leftLocationName = leftLocationName;
        this.rightLocationName = rightLocationName;
    }
    
    public String getLeftStreet(){
        return leftStreet;
    }
    
    public String getRightStreet(){
        return rightStreet;
    }
    
    public String getCurrentLocation(){
        return currentLocationName;
    }
    
    public Location getLeftLocation(){
        return leftLocation;
    }
    
    public Location getRightLocation(){
        return rightLocation;
    }
    
    public void setLocationRight(Location right){
        this.rightLocation = right;
    }
    
    public void setLocationLeft(Location left){
        this.leftLocation = left;
    }
    
    public void setOutSideCityName(String name){
        this.outsideCityName = name;
    }
    
    public String getOutSideCityName(){
        return outsideCityName;
    }
    
    public String toString(){
        return currentLocationName;
    }
    
    public String getLeftLocationName(){
        return leftLocationName;
    }
    
    public String getRightLocationName(){
        return rightLocationName;
    }
}
