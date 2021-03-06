package ca.ubc.cs.cpsc210.servicecard.model;

// Represents a card used to purchase food services at a university
public class FoodServicesCard {
    // NOTE TO CPSC 210 STUDENTS: normally, the 'final' keyword would be used in the declaration
    // of constants.  We omit it here to allow us to run tests against your code to
    // check that it works when different values are assigned.
    public static int POINTS_NEEDED_FOR_CASH_BACK = 2000;    // points needed to get cash-back reward
    public static int REWARD_POINTS_PER_CENT_CHARGED = 1;    // points earned for each cent charged to card
    public static int CASH_BACK_REWARD = 10;                 // reward in cents

    // add fields to represent changing properties of a food services card
    private int balance;
    private int Reward_Points;




    // REQUIRES: initialBalance >= 0
    // EFFECTS: constructs food service card with given initial balance in cents and zero reward points
    public FoodServicesCard(int initialBalance) {
        this.balance = initialBalance;
        this.Reward_Points = 0;
    }

    // REQUIRES: amount > 0
    // MODIFIES: this
    // EFFECTS: adds amount cents to balance on card
    public void reload(int amount) {
        this.balance = balance + amount;
    }

    // MODIFIES: this
    // EFFECTS: if there is sufficient balance on the account
    //            - subtracts amount cents from balance
    //            - adds reward points and then
    //                - if eligible, adds cash back reward(s) to account and deducts corresponding reward points
    //            - returns true
    //          otherwise, returns false
    public boolean makePurchase(int amount) {
        int Cents_To_Points = amount * REWARD_POINTS_PER_CENT_CHARGED;
        int Number_Of_Cash_Back = Cents_To_Points / POINTS_NEEDED_FOR_CASH_BACK;

        if (this.balance >= amount) {
            if (this.getRewardPoints() + Cents_To_Points >= POINTS_NEEDED_FOR_CASH_BACK)
                this.balance = balance - amount + (Number_Of_Cash_Back * CASH_BACK_REWARD);

            if (this.getRewardPoints() + Cents_To_Points >= POINTS_NEEDED_FOR_CASH_BACK)
                this.Reward_Points = Reward_Points + Cents_To_Points - (Number_Of_Cash_Back * POINTS_NEEDED_FOR_CASH_BACK);

            else {
                this.balance = balance - amount;
                this.Reward_Points = Reward_Points + Cents_To_Points;
            }
            return true;
        }
        else {
            return false;
        }
    }

    // EFFECTS: returns remaining balance in cents
    public int getBalance() {
        return this.balance;    // stub
    }

    // EFFECTS: returns number of unused reward points
    public int getRewardPoints() {
        return this.Reward_Points;    // stub
    }
}
