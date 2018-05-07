package ca.ubc.cs.cpsc210.machine.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the payment unit in a vending machine.
 */
public class PaymentUnit {
    private int numLoonies;   // number of loonies banked in machine for making change
    private int numQuarters;  // number of quarters banked in machine for making change
    private int numDimes;     // number of dimes banked in machine for making change
    private int numNickels;   // number of nickels banked in machine for making change
    private List<Coin> currentTransaction;   // list of coins inserted into machine during current transaction

    // EFFECTS: constructs a payment unit with no banked coins and no coins inserted into the machine
    // as part of a payment
    public PaymentUnit() {
        this.numLoonies = 0;
        this.numQuarters = 0;
        this.numDimes = 0;
        this.numNickels = 0;
        this.currentTransaction = new ArrayList<Coin>();
    }

    // MODIFIES: this
    // EFFECTS: clears all the coins banked in the unit
    public void clearCoinsBanked() {
        this.numLoonies = 0;
        this.numQuarters = 0;
        this.numDimes = 0;
        this.numNickels = 0;
    }

    // REQUIRES: number > 0
    // MODIFIES: this
    // EFFECTS: adds number coins of type c to the banked coins in the unit
    public void addCoinsToBanked(Coin c, int number) {
        switch (c) {
            case LOONIE:
                this.numLoonies += number;
                break;
            case QUARTER:
                this.numQuarters += number;
                break;
            case DIME:
                this.numDimes += number;
                break;
            //case NICKEL:
               // this.numNickels += number;
                //break;
            default:
                this.numNickels += number;
                break;
        }
    }

    // EFFECTS: returns number of coins banked of the given type
    public int getNumberOfCoinsBankedOfType(Coin c) {
        int number = 0;
        switch (c) {
            case LOONIE:
                number = this.numLoonies;
                break;

            case QUARTER:
                number = this.numQuarters;
                break;

            case DIME:
                number = this.numDimes;
                break;

            ///case NICKEL:
               // number = this.numNickels;
               // break;
            default:
                number += this.numNickels;
                break;
        }
       return number;
    }


    //EFFECTS: returns the total value of the given coin type
    public int getValueOfSpecificCoinType(Coin c) {
        int value = 0;
        switch (c) {
            case LOONIE:
                value = Coin.LOONIE.getValue() * this.numLoonies;
                break;

            case QUARTER:
                value = Coin.QUARTER.getValue() * this.numQuarters;
                break;

            case DIME:
                value = Coin.DIME.getValue() * this.numDimes;
                break;

            //case NICKEL:
              //  value = Coin.NICKEL.getValue() * this.numNickels;
              //  break;
            default:
                value = Coin.NICKEL.getValue() * this.numNickels;
                break;
        }
        return value;
    }

    // EFFECTS: returns the total value of all coins banked in the unit
    public int getValueOfCoinsBanked() {
        int total_value = this.getValueOfSpecificCoinType(Coin.LOONIE) + this.getValueOfSpecificCoinType(Coin.QUARTER)
                        + this.getValueOfSpecificCoinType(Coin.DIME) + this.getValueOfSpecificCoinType(Coin.NICKEL);

        return total_value;
    }

    // MODIFIES: this
    // EFFECTS: adds coin c to the unit as a part of a transaction
    public void insertCoin(Coin c) {
        this.currentTransaction.add(c);
    }

    // EFFECTS: returns value of coins inserted for current transaction
    public int getValueOfCoinsInserted() {
        int current_value = 0;

        for (Coin each : this.currentTransaction) {
            current_value += each.getValue();
        }

        return current_value;
    }


    // MODIFIES: this
    // EFFECTS: coins inserted for current transaction are cleared; list of coins
    // inserted for current transaction is returned in the order in which they were inserted.
    public List<Coin> cancelTransaction() {

        List<Coin> returnedTransaction = currentTransaction;
        currentTransaction = new ArrayList<Coin>();
        return returnedTransaction;
    }

    // REQUIRES: cost <= total value of coins inserted as part of current transaction
    // MODIFIES: this
    // EFFECTS: adds coins inserted to coins banked in unit and returns list of coins that will be provided as change.
    // Coins of largest possible value are used when determining the change.  Change in full is not guaranteed -
    // will provide only as many coins as are banked in the machine, without going over the amount due.
    public List<Coin> makePurchase(int cost) {
        List<Coin> returned = new ArrayList<Coin>();

        if (this.getValueOfCoinsInserted() == cost) {
            returned = addCoinTypeToChange(valueOfChange(cost));
            this.currentTransaction.clear();
            return returned;
        }
        else {
            bankCurrentTransactionCoins(this.currentTransaction);

            //if(cost < this.getValueOfCoinsBanked()) {
                returned = addCoinTypeToChange(valueOfChange(cost));
                this.currentTransaction.clear();

                return returned;

        }
            //return addCoinTypeToChange(valueOfChange(this.getValueOfCoinsBanked()));
        }

    //EFFECTS: adds inserted coins to the machine's coin bank
    public void bankCurrentTransactionCoins (List<Coin> coinlist) {
        for(Coin coin : coinlist) {
            if (coin.equals(Coin.LOONIE))
                this.numLoonies++;
            else if(coin.equals(Coin.QUARTER))
                this.numQuarters++;
            else if(coin.equals(Coin.DIME))
                this.numDimes++;
            else {
                this.numNickels++;
            }
        }
    }

    //EFFECTS: returns the total value of change from the purchase
    public int valueOfChange(int cost) {
        int change = 0;
        change = this.getValueOfCoinsInserted() - cost;
        return change;
    }


    public List<Coin> addCoinTypeToChange(int change_value) {

        int currentLoonieChangeValue = change_value;

        List<Coin> currentChangeCoinList = new ArrayList<Coin>();

        while(currentLoonieChangeValue - Coin.LOONIE.getValue() >= 0 && this.numLoonies > 0) {
            currentLoonieChangeValue -= Coin.LOONIE.getValue();
            currentChangeCoinList.add(Coin.LOONIE);
            this.numLoonies--;
        }

        int currentQuarterChangeValue = currentLoonieChangeValue;

        while(currentQuarterChangeValue - Coin.QUARTER.getValue() >= 0 && this.numQuarters > 0) {
            currentQuarterChangeValue -= Coin.QUARTER.getValue();
            currentChangeCoinList.add(Coin.QUARTER);
            this.numQuarters--;
        }

        int currentDimeChangeValue = currentQuarterChangeValue;

        while(currentDimeChangeValue - Coin.DIME.getValue() >= 0 && this.numDimes > 0) {
            currentDimeChangeValue -= Coin.DIME.getValue();
            currentChangeCoinList.add(Coin.DIME);
            this.numDimes--;
        }

        int currentNickelChangeValue = currentDimeChangeValue;

        while (currentNickelChangeValue - Coin.NICKEL.getValue() >= 0 && this.numNickels > 0) {
            currentNickelChangeValue -= Coin.NICKEL.getValue();
            currentChangeCoinList.add(Coin.NICKEL);
            this.numNickels--;
        }
        this.currentTransaction.clear();

        return currentChangeCoinList;

    }

}
