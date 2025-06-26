public class RegularMember extends GymMember //subClass RegularMember which inherits parent class GymMember
{
    // Six private attributes of subclass GymMember 
    private final int attendanceLimit;    
    private boolean isEligibleForUpgrade;
    private String removalReason;
    private String plan;
    private double price;
    
    // Constructor
    public RegularMember (int id, String name, String location, String phone, String email, String gender, String dateOfBirth, String membershipStartDate, String plan, double price)
    {
        super(id, name, location, phone, email, gender, dateOfBirth, membershipStartDate);
        this.isEligibleForUpgrade = false;
        this.attendanceLimit = 30;
        this.plan = "basic";
        this.price = 6500d;
        this.removalReason = "";
    }
    
    // Accessor method (getter)
    public boolean getIsEligibleForUpgrade()
    {
        return this.isEligibleForUpgrade;
    }
    public int getAttendanceLimit()
    {
        return this.attendanceLimit;
    }
    public String getPlan()
    {
        return this.plan;
    }
    public double getPrice()
    {
        return this.price;
    }
    public String getRemovalReason()
    {
        return this.removalReason;
    }
    
    /*Implementing the abstract method markAttendance() to increment
    the attendance value by 1 each time this method is invoked.*/
    @Override
    public void markAttendance()
    {
        if (activeStatus)
        {
            if (attendance < attendanceLimit)
            {
                attendance++;
                loyaltyPoints += 5; //For every visit loyalty points is increased by 5
            }
            if (attendance >= attendanceLimit)
            {
                this.isEligibleForUpgrade = true;
            }
        }
        else
        {
            System.out.println("Inactive membership! Please activate it first.");
        }
    }
    
    //Method to retrieve the price of the provided plan
    public double getPlanPrice(String plan)
    {
        switch (plan.toLowerCase())
        {
            case "basic":
                return 6500;
            
            case "standard":
                return 12500;
            
            case "deluxe":
                return 18500;
            
            default:
                return -1;
        }
    }
    
    // Method to upgrade the plan subscribed by the member
    public String upgradePlan(String plan)
    {
        if (!isEligibleForUpgrade) //Checks for eligibility of member
        {
            return "Upgrade not allowed!";
        }
        
        if (plan.equalsIgnoreCase(this.plan)) //checks if same plan is applied for upgrade
        {
            return "You are already subscribed to this plan.";
        }
        
        double price = getPlanPrice(plan);
        if (price == -1) // checks for validity of plan
        {
            return "Invalid plan selected.";
        }
        
        this.plan = plan; 
        this.price = price;
        return "Plan upgraded successfully to " + plan + " plan for amount " + price + ".";
    }
    
    // Method to change/revert member into regular member
    public void revertRegularMember(String removalReason)
    {
        resetMember();
        this.isEligibleForUpgrade = false;
        this.plan = "basic";
        this.price = 6500d;
        this.removalReason = removalReason;
    }
    
    // Method to display all the details of RegularMember
    @Override
    public String display()
    {
        String info = super.display(); // get base info from Member

        info += "Plan: " + this.plan + "\n";
        info += "Price: " + this.price + "\n";

        if (removalReason != null && !removalReason.isEmpty()) 
        {
            info += "Removal Reason: " + this.removalReason + "\n";
        }

        return info;
    }
}