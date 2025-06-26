public abstract class GymMember //abstract parent class
{
    //Attributes
    protected int id;
    protected String name;
    protected String location;
    protected String phone;
    protected String email;
    protected String gender;
    protected String dateOfBirth;
    protected String membershipStartDate;
    protected int attendance;
    protected double loyaltyPoints;
    protected boolean activeStatus;
    
    //Constructor
    public GymMember(int id, String name, String location, String phone, String email, String gender, String dateOfBirth, String membershipStartDate)
    {
        this.id = id;
        this.name = name;
        this.location = location;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.membershipStartDate = membershipStartDate;
        this.attendance = 0;
        this.loyaltyPoints = 0d;
        this.activeStatus = false;
    }
    //Accessor method (Getter)
    public int getId()
    {
        return this.id;
    }
    
    public String getName()
    {
        return this.name;
    }
    
    public String getLocation()
    {
        return this.location;
    }
    
    public String getPhone()
    {
        return this.phone;
    }
    
    public String getEmail()
    {
        return this.email;
    }
    
    public String getGender()
    {
        return this.gender;
    }
    
    public String getDateOfBirth()
    {
        return dateOfBirth;
    }
    
    public String getMembershipStartDate()
    {
        return this.membershipStartDate;
    }
    
    public int getAttendance()
    {
        return this.attendance;
    }
    
    public double getLoyaltyPoints()
    {
        return this.loyaltyPoints;
    }
    
    public boolean getActiveStatus()
    {
        return this.activeStatus;
    }
    
    //Abstract method to track attendance of the member
    public abstract void markAttendance();
    
    //method to activate or renew membership
    public void activateMembership()
    {
        this.activeStatus = true;
    }
    
    //method to deactivate or pause membership
    public void deactivateMembership()
    {
        if (activeStatus == true)
        {
            activeStatus = false;
        }
        else
        {
            System.out.println("The membership must be activated first in order to be deactivated.");
        }
    }
    
    //Method to reset activeStatus of the member to false, attendance and loyaltyPoints to zero
    public void resetMember()
    {
        this.activeStatus = false;
        this.attendance = 0;
        this.loyaltyPoints = 0d;
    }
    
    //display method (output)
    public String display()
    {
        return "Gym member details:\n"
         + "ID: " + this.id + "\n"
         + "Name: " + this.name + "\n"
         + "Location: " + this.location + "\n"
         + "Phone: " + this.phone + "\n"
         + "Email: " + this.email + "\n"
         + "Gender: " + this.gender + "\n"
         + "Date of birth: " + this.dateOfBirth + "\n"
         + "Membership start date: " + this.membershipStartDate + "\n"
         + "Attendance: " + this.attendance + "\n"
         + "Loyalty points: " + this.loyaltyPoints + "\n"
         + "Active status: " + (activeStatus ? "Active" : "Inactive") + "\n";
    }
}