package ca.ubc.cpsc210.booleancollection.model;

import java.util.ArrayList;
import java.util.List;

// Represents a collection of Boolean values
public class BooleanCollection {
    private List<Boolean> dataList;


    // EFFECTS: constructs empty collection of booleans
    public BooleanCollection() {
        this.dataList = new ArrayList<>();
    }


    // MODIFIES: this
    // EFFECTS: adds the boolean argument to the end of this
    public void add(Boolean argument) {
        this.dataList.add(argument);
    }

    //REQUIRES: index >= 0 & < this.getNumberOfItems
    //EFFECTS: returns the boolean value at index in this
    // index starts at 0
    public boolean get(int index) {
        return this.dataList.get(index);
    }


    //EFFECTS: returns number of booleans in this
    public int getNumberOfItems() {
        return this.dataList.size();
    }

    //EFFECTS: returns true if all booleans in this are true; otherwise return false
    public boolean areAllTrue() {
        boolean status = false;
       for(int i = 0; i < this.dataList.size(); i++) {
           if(this.dataList.get(i) == true) {
               status = true;
           }
           else {
               return false;
           }
       }
       return status;
    }

    //EFFECTS: return number of booleans that are true in boolean
    public int countTrue() {
        int indices = 0;
        for(Boolean single_boolean : dataList) {
            if(single_boolean == true) {
                indices++;
            }
        }
        return indices;
    }



    //----------------------------------------------------------------


    // EFFECTS: returns true if all the items in the collection are true, false otherwise
    public boolean areAllTrueRecursively() {
        return areAllTrueRecursivelyHelper(this.dataList);
    }

    // EFFECTS: returns true if all the items in list are true, false otherwise
    private boolean areAllTrueRecursivelyHelper(List<Boolean> list) {
        if(this.empty(list))
            return true;
        else if(this.first(list) == false){
            return false;
        }
        return areAllTrueRecursivelyHelper(rest(list));

    }

    // EFFECTS: produces true if list is empty, false otherwise
    private boolean empty(List<Boolean> list) {
        return list.size() == 0;
    }

    // REQUIRES: !empty(list)
    // EFFECTS: produces the first element in list
    private boolean first(List<Boolean> list) {
        return list.get(0);
    }

    // REQUIRES: !empty(list)
    // EFFECTS: produces the rest of (i.e. all but the first element of) list
    //          (note that modifications to the returned list will result
    //          in modifications to list)
    private List<Boolean> rest(List<Boolean> list) {
        return list.subList(1, list.size());
    }




}
