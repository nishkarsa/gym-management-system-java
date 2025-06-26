public class PremiumMember extends GymMember
{
    private final double premiumCharge;
    private String personalTrainer;
    private boolean isFullPayment;
    private double paidAmount;
    private double discountAmount;
    private String removalReason;
    
    //Constructor
    public PremiumMember (int id, String name, String location, String phone, String email, String gender, String dateOfBirth, String membershipStartDate, double premiumCharge, String personalTrainer)
    {
        super(id, name, location, phone, email, gender, dateOfBirth, membershipStartDate);
        this.premiumCharge = 50000d;
        this.paidAmount = 0d;
        this.isFullPayment = false;
        this.discountAmount = 0d;
        this.removalReason = "";
    }
    
    // Accessor method (Getter)
    public double getPremiumCharge()
    {
        return this.premiumCharge;
    }
    public String getPersonalTrainer()
    {
        return this.personalTrainer;
    }
    public boolean getIsFullPayment()
    {
        return this.isFullPayment;
    }
    public double getPaidAmount()
    {
        return this.paidAmount;
    }
    public double getDiscountAmount()
    {
        return this.discountAmount;
    }
    public String getRemovalReason()
    {
        return this.removalReason;
    }
    
    // Method to pay the due amount
    public String payDueAmount(double paidAmount)
    {
        if (isFullPayment)
        {
            return "No due amount.";
        }
        
        double dueAmount = this.paidAmount + paidAmount;
        
        if (dueAmount > premiumCharge)
        {
            return "Payment exceeds premium charge. Transaction Failed!";
        }
        
        this.paidAmount = dueAmount;
        double remainingAmount = premiumCharge - this.paidAmount;
        
        if (this.paidAmount == premiumCharge)
        {
            this.isFullPayment = true;
        }
        
        return "Payment successful! Remaining due amount: " + remainingAmount;
    }
    
    // Method to calculate discount amounts based on payment status
    public String calculateDiscount()
    {
        if (isFullPayment)
        {
            this.discountAmount = premiumCharge * 0.10;
            return "Discount applied successfully. Discount amount: " + this.discountAmount;
        }
        return "Not eligible for discount. Sorry!";
    }
    
    // Method to change/revert premium member
    public void revertPremiumMember(String removalReason)
    {
        super.resetMember();
        this.personalTrainer = "";
        this.isFullPayment = false;
        this.paidAmount = 0d;
        this.discountAmount = 0d;
        this.removalReason = removalReason;
    }
    
    // Method to display all details of premium member
    @Override
    public String display()
    {
        String info = super.display(); // Get the base member details

    info += "Personal Trainer: " + this.personalTrainer + "\n";
    info += "Paid Amount: " + this.paidAmount + "\n";
    info += "Full Payment Status: " + (isFullPayment ? "Yes" : "No") + "\n";

    double dueAmount = premiumCharge - this.paidAmount;
    info += "Due Amount to be Paid: " + dueAmount + "\n";

    if (isFullPayment) {
        info += "Discount Amount: " + this.discountAmount + "\n";
    }

    return info;
    }
    
    /*Implementing the abstract method markAttendance() to increment
    the attendance value by 1 each time this method is invoked.*/
    @Override
    public void markAttendance()
    {
        if (activeStatus)
        {
            attendance++;
            loyaltyPoints += 10; //for every attendance loyalty point is added by 10
        }
        else
        {
            System.out.println("Inactive membership! Please activate it first.");
        }
    }
}