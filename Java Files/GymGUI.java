import java.util.List;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.JComboBox;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.awt.Color.*;
import java.io.*;

public class GymGUI
{
    private ArrayList<GymMember> memberList = new ArrayList<>();
    
    private JFrame frame, displayFrame, readFrame;
    
    // Title
    private JLabel titleLabel; 
    
    // Add member section
    private JPanel addMemberPanel;
    private JButton regularButton, premiumButton;
    
    // Activate/Deactivate section
    private JPanel membershipPanel;
    private JLabel firstIdLabel;
    private JTextField firstMemberIdField;
    private JButton activateButton, deactivateButton, saveToFileButton, readFromFileButton;
    
    // Section for different member operations
    private JPanel operationPanel;
    private JLabel secondIdLabel;
    private JTextField secondMemberIdField, planDisplayField, removalReasonField, discountDisplayField, payDueAmountField;
    private JButton markAttendanceButton, upgradePlanButton, calculateDiscountButton, revertRegularMemberButton, revertPremiumMemberButton, payDueAmountButton;
    
    // Display member details section
    private JComboBox<String> displayCombo;
    private JButton memberDetailButton;
    
    // Components for Form
    private JPanel panel, regularFormPanel, premiumFormPanel;
    private JTextField regularIdField, regularNameField, regularAddressField, regularPhoneField, regularEmailField;
    private JTextField premiumIdField, premiumNameField, premiumAddressField, premiumPhoneField, premiumEmailField, premiumChargeField, personalTrainerField;
    private JRadioButton maleRadio, femaleRadio;
    private JComboBox<String> planCombo, priceCombo;
    private JComboBox<String> dayComboRegular, monthComboRegular, yearComboRegular;
    private JComboBox<String> admissionDayComboRegular, admissionMonthComboRegular, admissionYearComboRegular;
    private JComboBox<String> dayComboPremium, monthComboPremium, yearComboPremium;
    private JComboBox<String> admissionDayComboPremium, admissionMonthComboPremium, admissionYearComboPremium;
    private JButton saveRegularButton, clearRegularButton, savePremiumButton, clearPremiumButton;
    private ButtonGroup genderGroupRegular, genderGroupPremium;

    // Constructor
    public GymGUI()
    {
        // Frame for main menu
        frame = new JFrame("GymGUI");
        frame.setTitle("Cross Fit Gym Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,700);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null); //Absolute layout for precise positioning
        frame.getContentPane().setBackground(Color.BLACK);
        
        setUpComponents();
        addComponents();
        
        // Create form panels
        regularMemberForm();
        premiumMemberForm();
        
        // Initially hide both forms
        regularFormPanel.setVisible(false);
        premiumFormPanel.setVisible(false);
        
        // Add forms to frame
        frame.add(regularFormPanel);
        frame.add(premiumFormPanel);
        
        frame.setVisible(true);        
        
    }
    
    // Method to initialize/configure components
    private void setUpComponents()
    {
        Color red = new Color(200, 0, 0);
        Color green = new Color(0, 128, 0);
        Color blue = new Color(0, 0, 128);
        Font labelFont = new Font("Arial", Font.BOLD, 14);
        
        // For title
        titleLabel = new JLabel("Cross Fit where Fitness is your need", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setForeground(red);
        titleLabel.setBounds(100, 10, 600, 40);
        
        // Add member section
        addMemberPanel = new JPanel();
        addMemberPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(red), "Add member", TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.BOLD, 14), red));
        addMemberPanel.setLayout(new FlowLayout());
        addMemberPanel.setBounds(40, 70, 250, 80);
        addMemberPanel.setBackground(Color.BLACK);
        
        regularButton = new JButton("Regular Member");
        styleButton(regularButton, red);
        regularButton.addActionListener(new ActionListener()
        {
           @Override
           public void actionPerformed(ActionEvent e)
           {
                regularFormPanel.setVisible(true);
                premiumFormPanel.setVisible(false);
                frame.repaint(); // refresh layout
           }
        });
        premiumButton = new JButton("Premium Member");
        styleButton(premiumButton, red);
        premiumButton.addActionListener(new ActionListener()
        {
           @Override
           public void actionPerformed(ActionEvent e)
           {
               premiumFormPanel.setVisible(true);
               regularFormPanel.setVisible(false);
               frame.repaint(); // refresh layout
           }
        });
        
        // Activate/deactivate member section
        membershipPanel = new JPanel(null);
        membershipPanel.setBounds(40, 160, 250, 110);
        membershipPanel.setBackground(Color.BLACK);
        TitledBorder membershipBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(red), "Membership Activate/Deactivate", TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.BOLD, 14), red);
        membershipPanel.setBorder(membershipBorder);
        
        firstMemberIdField = new JTextField();
        firstMemberIdField.setBounds(120, 20, 110, 25);
        membershipPanel.add(label("Member ID:", 10, 20, labelFont, red));
        membershipPanel.add(firstMemberIdField);
        
        activateButton = new JButton("Activate");
        activateButton.setBounds(10, 55, 100, 25);
        styleButton(activateButton, red);
        
        // Action Listener to activate membership
        activateButton.addActionListener(new ActionListener()
        {
           @Override
           public void actionPerformed(ActionEvent e)
           {
               String idText = firstMemberIdField.getText().trim();
               
               if (idText.isEmpty())
               {
                   JOptionPane.showMessageDialog(null, "Please enter member ID.", "Warning", JOptionPane.WARNING_MESSAGE);
                   return;
               }
               
               try
               {
                   int enteredId = Integer.parseInt(idText);
                   boolean found = false;
                   
                   for (GymMember member : memberList)
                   {
                       if (member.getId() == enteredId)
                       {
                           member.activateMembership();
                           JOptionPane.showMessageDialog(null, "Membership activated for Member ID: " + enteredId, "Activation Completed", JOptionPane.INFORMATION_MESSAGE);
                           found = true;
                           break;
                       }
                   }
                   
                   if (!found)
                   {
                       JOptionPane.showMessageDialog(null, "No member found with ID: " + enteredId, "Error", JOptionPane.ERROR_MESSAGE);
                   }
               }
               
               catch (NumberFormatException ex)
               {
                   JOptionPane.showMessageDialog(null, "Invalid Member ID. Please enter a number.", "", JOptionPane.WARNING_MESSAGE);
               }
           }
        });
        
        deactivateButton = new JButton("Deactivate");
        deactivateButton.setBounds(120, 55, 110, 25);
        styleButton(deactivateButton, red);
        
        // Action Listener to deactivate membership
        deactivateButton.addActionListener(new ActionListener()
        {
           @Override
           public void actionPerformed(ActionEvent e)
           {
               String idText = firstMemberIdField.getText().trim();
               
               if (idText.isEmpty())
               {
                   JOptionPane.showMessageDialog(null, "Please enter member ID.", "Warning", JOptionPane.WARNING_MESSAGE);
                   return;
               }
               
               try
               {
                   int enteredId = Integer.parseInt(idText);
                   boolean found = false;
                   
                   for (GymMember member : memberList)
                   {
                       if (member.getId() == enteredId)
                       {
                           member.deactivateMembership();
                           JOptionPane.showMessageDialog(null, "Membership deactivated for Member ID: " + enteredId, "Deavtivation Completed", JOptionPane.INFORMATION_MESSAGE);
                           found = true;
                           break;
                       }
                   }
                   
                   if (!found)
                   {
                       JOptionPane.showMessageDialog(null, "No member found with ID: " + enteredId, "Error", JOptionPane.ERROR_MESSAGE);
                   }
               }
               
               catch (NumberFormatException ex)
               {
                   JOptionPane.showMessageDialog(null, "Invalid Member ID. Please enter a number.", "", JOptionPane.WARNING_MESSAGE);
               }
           }
        });
        
        saveToFileButton = new JButton("Save to File");
        saveToFileButton.setBounds(30, 310, 140, 25);
        styleButton(saveToFileButton, green);
        
        // Action Listener to save to file
        saveToFileButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                try (PrintWriter writer = new PrintWriter(new FileWriter("MemberDetails.txt"))) 
                {
                    // Header row
                    writer.printf("%-5s %-15s %-15s %-15s %-25s %-20s %-10s %-10s %-10s %15s %-10s %-15s %-15s %-15s%n",
                            "ID", "Name", "Location", "Phone", "Email", "Membership Start Date", "Plan", "Price",
                            "Attendance", "Loyalty Points", "Active", "Full Payment", "Discount Amount", "Net Amount Paid");
        
                    // Data rows
                    for (GymMember member : memberList) 
                    {
                        int id = member.getId();
                        String name = member.getName();
                        String location = member.getLocation();
                        String phone = member.getPhone();
                        String email = member.getEmail();
                        String startDate = member.getMembershipStartDate();
                        int attendance = member.getAttendance();
                        double loyaltyPoints = member.getLoyaltyPoints(); // Confirm this is double
                        String isActive = member.getActiveStatus() ? "Yes" : "No";
        
                        // Subclass-specific fields (set default values)
                        String plan = "-";
                        double price = 0.0;
                        String isFullPayment = "-";
                        double discount = 0.0;
                        double paidAmount = 0.0;
        
                        if (member instanceof RegularMember regular) 
                        {
                            plan = regular.getPlan();
                            price = regular.getPrice();
                        }
        
                        if (member instanceof PremiumMember premium) 
                        {
                            isFullPayment = premium.getIsFullPayment() ? "Yes" : "No";
                            discount = premium.getDiscountAmount();
                            paidAmount = premium.getPaidAmount();
                        }
        
                        writer.printf(
                            "%-5s %-15s %-15s %-15s %-25s %-20s %-10s %-10.2f %-10d %15.2f %-10s %-15s %-15.2f %-15.2f%n",
                            id, name, location, phone, email, startDate, plan, price, attendance,
                            loyaltyPoints, isActive, isFullPayment, discount, paidAmount);
                    }
        
                    JOptionPane.showMessageDialog(null, "Members exported successfully to MemberDetails.txt");
                } 
                catch (IOException ex) 
                {
                    JOptionPane.showMessageDialog(null, "Error writing to file: " + ex.getMessage());
                }
            }
        });
        
        readFromFileButton = new JButton("Read from File");
        readFromFileButton.setBounds(175, 310, 140, 25);
        styleButton(readFromFileButton, blue);
        
        // Action Listener to read from file
        readFromFileButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                // Columns must match the file header fields
                String[] columnNames = {
                    "ID", "Name", "Location", "Phone", "Email", "Membership Start Date",
                    "Plan", "Price", "Attendance", "Loyalty Points", "Active",
                    "Full Payment", "Discount Amount", "Net Amount Paid"
                };
        
                List<String[]> rows = new ArrayList<>();
        
                try (BufferedReader reader = new BufferedReader(new FileReader("MemberDetails.txt"))) 
                {
                    String line;
                    boolean isFirstLine = true;
                    while ((line = reader.readLine()) != null) 
                    {
                        // Skip header row
                        if (isFirstLine) 
                        {
                            isFirstLine = false;
                            continue;
                        }
                        // Split line by whitespace(s)
                        // NOTE: This is a simple approach, if your data has spaces, consider using a different delimiter
                        String[] parts = line.trim().split("\\s+");
        
                        // Sometimes spaces in email/name could break this; if so, adjust parsing or store data as CSV
                        if(parts.length == columnNames.length) 
                        {
                            rows.add(parts);
                        }
                    }
                } catch (IOException ex) 
                {
                    JOptionPane.showMessageDialog(null, "Error reading file: " + ex.getMessage());
                    return;
                }
        
                // Create Table Model with the data
                String[][] data = new String[rows.size()][];
                data = rows.toArray(data);
        
                JTable table = new JTable(data, columnNames);
                table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
                // Put table in scroll pane
                JScrollPane scrollPane = new JScrollPane(table);
                scrollPane.setPreferredSize(new Dimension(1100, 400));
        
                // Create new frame to show data
                JFrame readFrame = new JFrame("Member Details");
                readFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                readFrame.add(scrollPane);
                readFrame.pack();
                readFrame.setLocationRelativeTo(null);
                readFrame.setVisible(true);
            }
        });
        
        //Operations section
        operationPanel = new JPanel(null);
        operationPanel.setBounds(320, 70, 420, 230);
        operationPanel.setBackground(Color.BLACK);
        operationPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(red), "Member Operations", TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.BOLD, 14), red));
        
        secondMemberIdField = new JTextField();
        secondMemberIdField.setBounds(120, 20, 140, 25);
        operationPanel.add(label("Member ID:", 20, 20, labelFont, red));
        operationPanel.add(secondMemberIdField);
        
        markAttendanceButton = new JButton("Mark Attendance");
        markAttendanceButton.setBounds(20, 50, 380, 25);
        styleButton(markAttendanceButton, red);
        
        // Action Listener to mark attendance
        markAttendanceButton.addActionListener(new ActionListener()
        {
           @Override
           public void actionPerformed(ActionEvent e)
           {
               String idText = secondMemberIdField.getText().trim();
               
               if (idText.isEmpty())
               {
                   JOptionPane.showMessageDialog(null,"Please enter a Member ID.", "Error", JOptionPane.ERROR_MESSAGE);
                   return;
               }
               
               try
               {
                   int enteredId = Integer.parseInt(idText);
                   boolean found = false;
                   
                   for (GymMember member : memberList)
                   {
                       if (member.getId() == enteredId)
                       {
                           found = true;
                           if (member.getActiveStatus())
                           {
                               member.markAttendance();
                               JOptionPane.showMessageDialog(null, "Attendance marked for Member ID: " + enteredId, "", JOptionPane.INFORMATION_MESSAGE);
                           }
                           else
                           {
                               JOptionPane.showMessageDialog(null, "Membership is not active. Cannot mark attendance.", "Warning", JOptionPane.WARNING_MESSAGE);
                           }
                           break;
                       }
                   }
                   if (!found)
                   {
                       JOptionPane.showMessageDialog(null, "No member found with ID: " + enteredId, "Warning", JOptionPane.WARNING_MESSAGE);
                   }
               }
               catch (NumberFormatException ex)
               {
                   JOptionPane.showMessageDialog(null, "Invalid Member ID. Please enter a number.", "Error", JOptionPane.ERROR_MESSAGE);
               }
           }
        });
        
        planDisplayField = new JTextField();
        planDisplayField.setBounds(20, 80, 100, 25);
        planDisplayField.setEditable(false);
        operationPanel.add(planDisplayField);
        
        upgradePlanButton = new JButton("Upgrade Plan");
        upgradePlanButton.setBounds(125, 80, 275, 25);
        styleButton(upgradePlanButton, red);
        
        // Action Listener to Upgrade plan
        upgradePlanButton.addActionListener(new ActionListener()
        {
           @Override
           public void actionPerformed (ActionEvent e)
           {
               String idText = secondMemberIdField.getText().trim();
               
               if (idText.isEmpty())
               {
                   JOptionPane.showMessageDialog(null,"Please enter a Member ID.", "Error", JOptionPane.ERROR_MESSAGE);
                   return;
               }
               
               try
               {
                   int enteredId = Integer.parseInt(idText);
                   String selectedPlan = (String) planCombo.getSelectedItem();
                   boolean found = false;
                   
                   for (GymMember member : memberList)
                   {
                       if (member.getId() == enteredId)
                       {
                           found = true;
                           
                           if (member instanceof RegularMember)
                           {
                                RegularMember regular = (RegularMember) member;
                               
                                if (regular.getActiveStatus())
                                {
                                    planDisplayField.setText(regular.getPlan());
                                    String output = regular.upgradePlan(selectedPlan);
                                    JOptionPane.showMessageDialog(null, output);
                                }
                                else
                                {
                                JOptionPane.showMessageDialog(null, "Membership is not active. Cannot upgrade plan.", "Warning", JOptionPane.WARNING_MESSAGE);
                                }
                           }
                           else
                           {
                               JOptionPane.showMessageDialog(null, "Member is not a regular member. Plan upgrade not allowed.", "Warning", JOptionPane.WARNING_MESSAGE);
                           }
                           break;
                       }
                   }
                   if (!found)
                   {
                       JOptionPane.showMessageDialog(null, "No member found with ID: " + enteredId, "Warning", JOptionPane.WARNING_MESSAGE);
                   }
               }
               catch (NumberFormatException ex)
               {
                   JOptionPane.showMessageDialog(null, "Invalid Member ID. Please enter a number.", "Error", JOptionPane.ERROR_MESSAGE);
               } 
           }
        });
        
        discountDisplayField = new JTextField();
        discountDisplayField.setBounds(20, 110, 100, 25);
        discountDisplayField.setEditable(false);
        operationPanel.add(discountDisplayField);
        
        calculateDiscountButton = new JButton("Calculate Discount");
        calculateDiscountButton.setBounds(125, 110, 275, 25);
        styleButton(calculateDiscountButton, red);
        
        // Action Listener to calculate discount
        calculateDiscountButton.addActionListener(new ActionListener()
        {
           @Override
           public void actionPerformed(ActionEvent e)
           {
               String idText = secondMemberIdField.getText().trim();
               
               if (idText.isEmpty())
               {
                   JOptionPane.showMessageDialog(null,"Please enter a Member ID.", "Error", JOptionPane.ERROR_MESSAGE);
                   return;
               }
               
               try
               {
                   int enteredId = Integer.parseInt(idText);
                   boolean found = false;
                   
                   for (GymMember member : memberList)
                   {
                       if (member.getId() == enteredId)
                       {
                           found = true;
                           
                           if (member instanceof PremiumMember)
                           {
                               PremiumMember premium = (PremiumMember) member;
                           
                               if (member.getActiveStatus())
                               {
                                   double discount = premium.getDiscountAmount();
                                   String output = premium.calculateDiscount();     // Calls the logic
                                   discountDisplayField.setText(String.valueOf(discount));
                                   JOptionPane.showMessageDialog(null, output); 
                               }
                               else
                               {
                               JOptionPane.showMessageDialog(null, "Membership is not active. Cannot calculate discount.", "Warning", JOptionPane.WARNING_MESSAGE);
                               }
                           }
                           else
                           {
                               JOptionPane.showMessageDialog(null, "Member is not a Premium Member.", "Warning", JOptionPane.WARNING_MESSAGE);
                           }
                           break;
                       }
                   }
                   if (!found)
                   {
                       JOptionPane.showMessageDialog(null, "No member found with ID: " + enteredId, "Warning", JOptionPane.WARNING_MESSAGE);
                   }
               }
               catch (NumberFormatException ex)
               {
                   JOptionPane.showMessageDialog(null, "Invalid Member ID. Please enter a number.", "Error", JOptionPane.ERROR_MESSAGE);
               }
           }
        });
        
        removalReasonField = new JTextField();
        removalReasonField.setBounds(20, 140, 100, 25);
        operationPanel.add(label(":Removal Reason", 125, 140, new Font("Arial", Font.BOLD, 12), red));
        operationPanel.add(removalReasonField);
        
        revertRegularMemberButton = new JButton("Revert Regular Member");
        revertRegularMemberButton.setBounds(20, 170, 185, 25);
        styleButton(revertRegularMemberButton, red);
        
        // Action Listener to revert regular member
        revertRegularMemberButton.addActionListener(new ActionListener()
        {
           @Override
           public void actionPerformed(ActionEvent e)
           {
               String idText = secondMemberIdField.getText().trim();
               
               if (idText.isEmpty())
               {
                   JOptionPane.showMessageDialog(null,"Please enter a Member ID.", "Error", JOptionPane.ERROR_MESSAGE);
                   return;
               }
               
               try
               {
                   int enteredId = Integer.parseInt(idText);
                   String reason = removalReasonField.getText().trim();
                   boolean found = false;
                   
                   if (reason.isEmpty())
                   {
                       JOptionPane.showMessageDialog(null, "Please provide a reason for removal.", "Warning", JOptionPane.WARNING_MESSAGE);
                       return;
                   }
                   for (GymMember member : memberList)
                   {
                       if (member.getId() == enteredId)
                       {
                           found = true;
                           
                           if (member instanceof RegularMember)
                           {
                               RegularMember regular = (RegularMember) member;
                           
                               if (member.getActiveStatus())
                               {
                                   regular.revertRegularMember(reason);
                                   JOptionPane.showMessageDialog(null, "Regular Member reverted.\nReason: " + reason, "", JOptionPane.INFORMATION_MESSAGE);
                               }
                               else
                               {
                               JOptionPane.showMessageDialog(null, "Membership is not active. Cannot revert membership.", "Warning", JOptionPane.WARNING_MESSAGE);
                               }
                           }
                           else
                           {
                               JOptionPane.showMessageDialog(null, "Member is not a Regular Member.", "Warning", JOptionPane.WARNING_MESSAGE);
                           }
                           break;
                       }
                   }
                   if (!found)
                   {
                       JOptionPane.showMessageDialog(null, "No member found with ID: " + enteredId, "Warning", JOptionPane.WARNING_MESSAGE);
                   }
               }
               catch (NumberFormatException ex)
               {
                   JOptionPane.showMessageDialog(null, "Invalid Member ID. Please enter a number.", "Error", JOptionPane.ERROR_MESSAGE);
               }
           }
        });
        
        revertPremiumMemberButton = new JButton("Revert Premium Member");
        revertPremiumMemberButton.setBounds(210, 170, 190, 25);
        styleButton(revertPremiumMemberButton, red);
        
        // Action Listener to revert premium member
        revertPremiumMemberButton.addActionListener(new ActionListener()
        {
           @Override
           public void actionPerformed(ActionEvent e)
           {
               String idText = secondMemberIdField.getText().trim();
               
               if (idText.isEmpty())
               {
                   JOptionPane.showMessageDialog(null,"Please enter a Member ID.", "Error", JOptionPane.ERROR_MESSAGE);
                   return;
               }
               
               try
               {
                   int enteredId = Integer.parseInt(idText);
                   String reason = removalReasonField.getText().trim();
                   boolean found = false;
                   
                   if (reason.isEmpty())
                   {
                       JOptionPane.showMessageDialog(null, "Please provide a reason for removal.", "Warning", JOptionPane.WARNING_MESSAGE);
                       return;
                   }
                   for (GymMember member : memberList)
                   {
                       if (member.getId() == enteredId)
                       {
                           found = true;
                           
                           if (member instanceof PremiumMember)
                           {
                               PremiumMember premium = (PremiumMember) member;
                           
                               if (member.getActiveStatus())
                               {
                                   premium.revertPremiumMember(reason);
                                   JOptionPane.showMessageDialog(null, "Premium Member reverted.\nReason: " + reason, "", JOptionPane.INFORMATION_MESSAGE);
                               }
                               else
                               {
                               JOptionPane.showMessageDialog(null, "Membership is not active. Cannot revert membership.", "Warning", JOptionPane.WARNING_MESSAGE);
                               }
                           }
                           else
                           {
                               JOptionPane.showMessageDialog(null, "Member is not a Premium Member.", "Warning", JOptionPane.WARNING_MESSAGE);
                           }
                           break;
                       }
                   }
                   if (!found)
                   {
                       JOptionPane.showMessageDialog(null, "No member found with ID: " + enteredId, "Warning", JOptionPane.WARNING_MESSAGE);
                   }
               }
               catch (NumberFormatException ex)
               {
                   JOptionPane.showMessageDialog(null, "Invalid Member ID. Please enter a number.", "Error", JOptionPane.ERROR_MESSAGE);
               }
           }
        });
        
        payDueAmountField = new JTextField();
        payDueAmountField.setBounds(20, 200, 100, 25);
        operationPanel.add(payDueAmountField);
        
        payDueAmountButton = new JButton("Pay Due Amount");
        payDueAmountButton.setBounds(125, 200, 275, 25);
        styleButton(payDueAmountButton, red);
        
        // Action Listener to pay due amount
        payDueAmountButton.addActionListener(new ActionListener()
        {
           @Override
           public void actionPerformed(ActionEvent e)
           {
               String idText = secondMemberIdField.getText().trim();
               String amountText = payDueAmountField.getText().trim();
               
               if (idText.isEmpty() || amountText.isEmpty())
               {
                   JOptionPane.showMessageDialog(null,"Please enter both Member ID and amount.", "Error", JOptionPane.ERROR_MESSAGE);
                   return;
               }
               
               double amount;
               try
               {
                   amount = Double.parseDouble(amountText);
               }
               catch(NumberFormatException ex)
               {
                   JOptionPane.showMessageDialog(null, "Please enter a valid number for the amount.", "Error", JOptionPane.ERROR_MESSAGE);
                   return;
               }
               
               try
               {
                   int enteredId = Integer.parseInt(idText);
                   boolean found = false;
                   
                   for (GymMember member : memberList)
                   {
                       if (member.getId() == enteredId)
                       {
                           found = true;
                           
                           if (member instanceof PremiumMember)
                           {
                               PremiumMember premium = (PremiumMember) member;
                           
                               if (member.getActiveStatus())
                               {
                                   String output = premium.payDueAmount(amount);
                                   JOptionPane.showMessageDialog(null, output);
                               }
                               else
                               {
                               JOptionPane.showMessageDialog(null, "Membership is not active. Cannot pay due amount.", "Warning", JOptionPane.WARNING_MESSAGE);
                               }
                           }
                           else
                           {
                               JOptionPane.showMessageDialog(null, "Member is not a Premium Member.", "Warning", JOptionPane.WARNING_MESSAGE);
                           }
                           break;
                       }
                   }
                   if (!found)
                   {
                       JOptionPane.showMessageDialog(null, "No member found with ID: " + enteredId, "Warning", JOptionPane.WARNING_MESSAGE);
                   }
               }
               catch (NumberFormatException ex)
               {
                   JOptionPane.showMessageDialog(null, "Invalid Member ID. Please enter a number.", "Error", JOptionPane.ERROR_MESSAGE);
               }
           }
        });
        
        // Member details display section
        displayCombo = new JComboBox<>(new String[]{"Regular Member", "Premium Member"});
        displayCombo.setBounds(320, 310, 200, 30);
        displayCombo.setBackground(Color.DARK_GRAY);
        displayCombo.setForeground(Color.WHITE);
        
        memberDetailButton = new JButton("Member Details");
        memberDetailButton.setBounds(540, 310, 150, 30);
        styleButton(memberDetailButton, red);
        
        displayFrame = new JFrame("Display Members");
        displayFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        displayFrame.setSize(600, 500);
        displayFrame.setLayout(new BorderLayout());
        
        // Action Listener to display member details
        memberDetailButton.addActionListener(new ActionListener()
        {
           @Override
           public void actionPerformed(ActionEvent e)
           {
               try
               {
                   String selectedType = (String) displayCombo.getSelectedItem();
                   
                   displayFrame.getContentPane().removeAll();
                   
                   JTextArea textArea = new JTextArea();
                   textArea.setEditable(false);
                   
                   for (GymMember member : memberList)
                   {
                       if ("Regular Member".equals(selectedType) && member instanceof RegularMember)
                       {
                           RegularMember regular = (RegularMember) member;
                           textArea.append(regular.display() + "\n---------------------------------\n");
                       }
                       else if ("Premium Member".equals(selectedType) && member instanceof PremiumMember)
                       {
                           PremiumMember premium = (PremiumMember) member;
                           textArea.append(premium.display() + "\n---------------------------------\n");
                       }
                   }
                   JScrollPane scrollPane = new JScrollPane(textArea);
                   displayFrame.add(scrollPane, BorderLayout.CENTER);
                   displayFrame.revalidate();
                   displayFrame.repaint();
                   displayFrame.setVisible(true);
               }
               catch (Exception ex)
               {
                   ex.printStackTrace();
               }
            }
        });
    }
    
    // Method to add components to the frame
    private void addComponents()
    {
        // Title section
        frame.add(titleLabel);
        frame.add(addMemberPanel);
        addMemberPanel.add(regularButton);
        addMemberPanel.add(premiumButton);
        
        // Add member section
        frame.add(membershipPanel);
        membershipPanel.add(firstMemberIdField);
        membershipPanel.add(activateButton);
        membershipPanel.add(deactivateButton);
        
        // Member operation section
        frame.add(operationPanel);
        operationPanel.add(secondMemberIdField);
        operationPanel.add(markAttendanceButton);
        operationPanel.add(upgradePlanButton);
        operationPanel.add(calculateDiscountButton);
        operationPanel.add(revertRegularMemberButton);
        operationPanel.add(revertPremiumMemberButton);
        operationPanel.add(payDueAmountButton);
        
        // Display detail section
        frame.add(displayCombo);
        frame.add(memberDetailButton);
        frame.add(saveToFileButton);
        frame.add(readFromFileButton);
    }
    
    // Method to add name label in form
    private JLabel addLabel(JPanel panel, String text, int x, int y, Font font, Color color)
    {
        JLabel label = new JLabel(text);
        label.setFont(font);
        label.setForeground(color);
        label.setBounds(x, y, 120, 25);
        panel.add(label);
        return label;
    }
    
    // Method to add text field in form
    private JTextField addTextField(JPanel panel, int x, int y)
    {
        JTextField textField = new JTextField();
        textField.setBounds(x, y, 120, 25);
        panel.add(textField);
        return textField;
    }
    // Method for regular member form
    private void regularMemberForm()
    {
        Color red = new Color(200, 0, 0);
        Color green = new Color(0, 128, 0);
        Font labelFont = new Font("Arial", Font.BOLD, 14);
        
        regularFormPanel = new JPanel(null);
        regularFormPanel.setBounds(40, 340, 700, 250);
        regularFormPanel.setBackground(Color.BLACK);
        regularFormPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(red), "Regular Member Form", TitledBorder.LEFT, TitledBorder.TOP, labelFont, red));
        
        // Left side labels and fields
        addLabel(regularFormPanel, "ID", 20, 30, labelFont, red);
        regularIdField = addTextField(regularFormPanel, 120, 30);
        
        addLabel(regularFormPanel, "Address", 20, 70, labelFont, red);
        regularAddressField = addTextField(regularFormPanel, 120, 70);
        
        addLabel(regularFormPanel, "E-mail", 20, 110, labelFont, red);
        regularEmailField = addTextField(regularFormPanel, 120, 110);
        
        addLabel(regularFormPanel, "Date of Birth", 20, 150, labelFont, red);
        String[] days = new String[31];
        for (int i = 0; i <31; i++)
        days[i] = String.valueOf(i + 1);
        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        String[] years = {"1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007"};
        dayComboRegular = new JComboBox<>(days);
        dayComboRegular.setBounds(120, 150, 40, 25);
        monthComboRegular = new JComboBox<>(months);
        monthComboRegular.setBounds(170, 150, 60, 25);
        yearComboRegular = new JComboBox<>(years);
        yearComboRegular.setBounds(240, 150, 60, 25);
        regularFormPanel.add(dayComboRegular);
        regularFormPanel.add(monthComboRegular);
        regularFormPanel.add(yearComboRegular);
        
        addLabel(regularFormPanel, "Plan", 20, 190, labelFont, red);
        planCombo = new JComboBox<>(new String[]{"Basic", "Standard", "Deluxe"});
        planCombo.setBounds(120, 190, 120, 25);
        regularFormPanel.add(planCombo);
        
        // Right side labels and fields
        addLabel(regularFormPanel, "Name", 320, 30, labelFont, red);
        regularNameField = addTextField(regularFormPanel, 420, 30);
        
        addLabel(regularFormPanel, "Phone", 320, 70, labelFont, red);
        regularPhoneField = addTextField(regularFormPanel, 420, 70);
        
        addLabel(regularFormPanel, "Gender", 320, 110, labelFont, red);
        maleRadio = new JRadioButton("Male");
        femaleRadio = new JRadioButton("Female");
        genderGroupRegular = new ButtonGroup();
        genderGroupRegular.add(maleRadio);
        genderGroupRegular.add(femaleRadio);
        maleRadio.setBounds(420, 110, 60, 25);
        femaleRadio.setBounds(490, 110, 80, 25);
        regularFormPanel.add(maleRadio);
        regularFormPanel.add(femaleRadio);
        
        addLabel(regularFormPanel, "Date", 320, 150, labelFont, red);
        String[] admissionDays = new String[31];
        for (int i = 0; i <31; i++)
        admissionDays[i] = String.valueOf(i + 1);
        String[] admissionMonths = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        String[] admissionYears = {"2025", "2026", "2027", "2028", "2029", "2030"};
        admissionDayComboRegular = new JComboBox<>(admissionDays);
        admissionDayComboRegular.setBounds(420, 150, 40, 25);
        admissionMonthComboRegular = new JComboBox<>(admissionMonths);
        admissionMonthComboRegular.setBounds(470, 150, 60, 25);
        admissionYearComboRegular = new JComboBox<>(admissionYears);
        admissionYearComboRegular.setBounds(540, 150, 60, 25);
        regularFormPanel.add(admissionDayComboRegular);
        regularFormPanel.add(admissionMonthComboRegular);
        regularFormPanel.add(admissionYearComboRegular);
        
        addLabel(regularFormPanel, "Price", 320, 190, labelFont, red);
        priceCombo = new JComboBox<>(new String[]{"6500", "12500", "18500"});
        priceCombo.setBounds(420, 190, 120, 25);
        regularFormPanel.add(priceCombo);
        
        //Buttons
        saveRegularButton = new JButton("Save");
        saveRegularButton.setBounds(200, 220, 100, 30);
        styleButton(saveRegularButton, green);
        regularFormPanel.add(saveRegularButton);
        
        // Action listener to create object of regular form when save button is clicked
        saveRegularButton.addActionListener(new ActionListener()
        {
           @Override
           public void actionPerformed(ActionEvent e)
           {
               String idText = regularIdField.getText().trim();
               String name = regularNameField.getText().trim();
               String address = regularAddressField.getText().trim();
               String phone = regularPhoneField.getText().trim();
               String email = regularEmailField.getText().trim();
               String gender = maleRadio.isSelected() ? "Male" : (femaleRadio.isSelected() ? "Female" : "");
               String dateOfBirth = dayComboRegular.getSelectedItem() + "-" + monthComboRegular.getSelectedItem() + "-" + yearComboRegular.getSelectedItem(); 
               String admissionDate = admissionDayComboRegular.getSelectedItem() + "-" + admissionMonthComboRegular.getSelectedItem() + "-" + admissionYearComboRegular.getSelectedItem();
               String plan = (String) planCombo.getSelectedItem();
               String priceText = (String) priceCombo.getSelectedItem();
               
               // Check if ID is empty
               if (idText.isEmpty())
               {
                   JOptionPane.showMessageDialog(null, "Member ID is required.", "Warning", JOptionPane.WARNING_MESSAGE);
                   return;
               }
               
               // CHeck for duplicate ID
               int id;
               try
               {
                   id = Integer.parseInt(idText);
               }
               catch (NumberFormatException ex)
               {
                   JOptionPane.showMessageDialog(null, "Please enter a valid numeric ID.", "Error", JOptionPane.ERROR_MESSAGE);
                   return;
               }
               
               for (GymMember member : memberList)
               {
                   if (member.getId() == id)
                   {
                       JOptionPane.showMessageDialog(null, "Member ID already exists. Please use unique ID.", "Warning", JOptionPane.WARNING_MESSAGE);
                       return;
                   }
               }           
               int price = Integer.parseInt(priceText);
               
               // Save form to regular member
               GymMember newMember = new RegularMember(id, name, address, phone, email, gender, dateOfBirth, admissionDate, plan, price);
               memberList.add(newMember);
               
               JOptionPane.showMessageDialog(null, "Regular Member added Successfully", "", JOptionPane.INFORMATION_MESSAGE);
               
               regularIdField.setText("");
                regularNameField.setText("");
                regularAddressField.setText("");
                regularPhoneField.setText("");
                regularEmailField.setText("");
                dayComboRegular.setSelectedIndex(0);
                monthComboRegular.setSelectedIndex(0);
                yearComboRegular.setSelectedIndex(0);
                admissionDayComboRegular.setSelectedIndex(0);
                admissionMonthComboRegular.setSelectedIndex(0);
                admissionYearComboRegular.setSelectedIndex(0);
                genderGroupRegular.clearSelection();
                planCombo.setSelectedIndex(0);
                priceCombo.setSelectedIndex(0);
           }
        });
        
        clearRegularButton = new JButton("Clear");
        clearRegularButton.setBounds(350, 220, 100, 30);
        styleButton(clearRegularButton, red);
        regularFormPanel.add(clearRegularButton);
        clearRegularButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                regularIdField.setText("");
                regularNameField.setText("");
                regularAddressField.setText("");
                regularPhoneField.setText("");
                regularEmailField.setText("");
                dayComboRegular.setSelectedIndex(0);
                monthComboRegular.setSelectedIndex(0);
                yearComboRegular.setSelectedIndex(0);
                admissionDayComboRegular.setSelectedIndex(0);
                admissionMonthComboRegular.setSelectedIndex(0);
                admissionYearComboRegular.setSelectedIndex(0);
                genderGroupRegular.clearSelection();
                planCombo.setSelectedIndex(0);
                priceCombo.setSelectedIndex(0);
            }
        });
        regularFormPanel.setVisible(false);
    }
    
    // Method for premium member form
    private void premiumMemberForm()
    {
        Color red = new Color(200, 0, 0);
        Color green = new Color(0, 128, 0);
        Font labelFont = new Font("Arial", Font.BOLD, 14);
        
        premiumFormPanel = new JPanel(null);
        premiumFormPanel.setBounds(40, 340, 700, 250);
        premiumFormPanel.setBackground(Color.BLACK);
        premiumFormPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(red), "Premium Member Form", TitledBorder.LEFT, TitledBorder.TOP, labelFont, red));
        
        // Left side labels and fields
        addLabel(premiumFormPanel, "ID", 20, 30, labelFont, red);
        premiumIdField = addTextField(premiumFormPanel, 140, 30);
        
        addLabel(premiumFormPanel, "Address", 20, 70, labelFont, red);
        premiumAddressField = addTextField(premiumFormPanel, 140, 70);
        
        addLabel(premiumFormPanel, "E-mail", 20, 110, labelFont, red);
        premiumEmailField = addTextField(premiumFormPanel, 140, 110);
        
        addLabel(premiumFormPanel, "Date of Birth", 20, 150, labelFont, red);
        String[] days = new String[31];
        for (int i = 0; i <31; i++)
        days[i] = String.valueOf(i + 1);
        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        String[] years = {"1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007"};
        dayComboPremium = new JComboBox<>(days);
        dayComboPremium.setBounds(140, 150, 40, 25);
        monthComboPremium = new JComboBox<>(months);
        monthComboPremium.setBounds(190, 150, 60, 25);
        yearComboPremium = new JComboBox<>(years);
        yearComboPremium.setBounds(260, 150, 60, 25);
        premiumFormPanel.add(dayComboPremium);
        premiumFormPanel.add(monthComboPremium);
        premiumFormPanel.add(yearComboPremium);
        
        addLabel(premiumFormPanel, "Premium Charge", 20, 190, labelFont, red);
        premiumChargeField = addTextField(premiumFormPanel, 140, 190);
        
        // Right side labels and fields
        addLabel(premiumFormPanel, "Name", 340, 30, labelFont, red);
        premiumNameField = addTextField(premiumFormPanel, 460, 30);
        
        addLabel(premiumFormPanel, "Phone", 340, 70, labelFont, red);
        premiumPhoneField = addTextField(premiumFormPanel, 460, 70);
        
        addLabel(premiumFormPanel, "Gender", 340, 110, labelFont, red);
        maleRadio = new JRadioButton("Male");
        femaleRadio = new JRadioButton("Female");
        genderGroupPremium = new ButtonGroup();
        genderGroupPremium.add(maleRadio);
        genderGroupPremium.add(femaleRadio);
        maleRadio.setBounds(460, 110, 60, 25);
        femaleRadio.setBounds(530, 110, 80, 25);
        premiumFormPanel.add(maleRadio);
        premiumFormPanel.add(femaleRadio);
        
        addLabel(premiumFormPanel, "Date", 340, 150, labelFont, red);
        String[] admissionDays = new String[31];
        for (int i = 0; i <31; i++)
        admissionDays[i] = String.valueOf(i + 1);
        String[] admissionMonths = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        String[] admissionYears = {"2025", "2026", "2027", "2028", "2029", "2030"};
        admissionDayComboPremium = new JComboBox<>(admissionDays);
        admissionDayComboPremium.setBounds(460, 150, 40, 25);
        admissionMonthComboPremium = new JComboBox<>(admissionMonths);
        admissionMonthComboPremium.setBounds(510, 150, 60, 25);
        admissionYearComboPremium = new JComboBox<>(admissionYears);
        admissionYearComboPremium.setBounds(580, 150, 60, 25);
        premiumFormPanel.add(admissionDayComboPremium);
        premiumFormPanel.add(admissionMonthComboPremium);
        premiumFormPanel.add(admissionYearComboPremium);
        
        addLabel(premiumFormPanel, "Personal Trainer", 340, 190, labelFont, red);
        personalTrainerField = addTextField(premiumFormPanel, 460, 190);
        
        //Buttons
        savePremiumButton = new JButton("Save");
        savePremiumButton.setBounds(200, 220, 100, 30);
        styleButton(savePremiumButton, green);
        premiumFormPanel.add(savePremiumButton);
        
        // Action listener to create object of premium form when save button is clicked
        savePremiumButton.addActionListener(new ActionListener()
        {
           @Override
           public void actionPerformed(ActionEvent e)
           {
               //int id = Integer.parseInt(regularIdField.getText());
               String idText = premiumIdField.getText();
               String name = premiumNameField.getText().trim();
               String address = premiumAddressField.getText().trim();
               String phone = premiumPhoneField.getText().trim();
               String email = premiumEmailField.getText().trim();
               String gender = maleRadio.isSelected() ? "Male" : (femaleRadio.isSelected() ? "Female" : "");
               String dateOfBirth = dayComboPremium.getSelectedItem() + "" + monthComboPremium.getSelectedItem() + "" + yearComboPremium.getSelectedItem(); 
               String admissionDate = admissionDayComboPremium.getSelectedItem() + "" + admissionMonthComboPremium.getSelectedItem() + "" + admissionYearComboPremium.getSelectedItem();
               String premiumChargeText = premiumChargeField.getText();
               String personalTrainer = personalTrainerField.getText();
               
               // Check if ID is empty
               if (idText.isEmpty())
               {
                   JOptionPane.showMessageDialog(null, "Member ID is required.", "Warining", JOptionPane.WARNING_MESSAGE);
                   return;
               }
               
               // CHeck for duplicate ID
               try
               {
                   int id = Integer.parseInt(idText);
               }
               catch (NumberFormatException ex)
               {
                   JOptionPane.showMessageDialog(null, "Please enter a valid numeric ID.", "Error", JOptionPane.ERROR_MESSAGE);
               }
               int id = Integer.parseInt(idText); 
               for (GymMember member : memberList)
               {
                   if (member.getId() == id)
                   {
                       JOptionPane.showMessageDialog(null, "Member ID already exists. Please use unique ID.", "Warning", JOptionPane.WARNING_MESSAGE);
                       return;
                   }
               }           
               int premiumCharge = Integer.parseInt(premiumChargeText);
               // Save form to premium member
               GymMember newMember = new PremiumMember(id, name, address, phone, email, gender, dateOfBirth, admissionDate, premiumCharge, personalTrainer);
               memberList.add(newMember);
               
               JOptionPane.showMessageDialog(null, "Premium Member added Successfully", "", JOptionPane.INFORMATION_MESSAGE);
               
               premiumIdField.setText("");
                premiumNameField.setText("");
                premiumAddressField.setText("");
                premiumPhoneField.setText("");
                premiumEmailField.setText("");
                dayComboPremium.setSelectedIndex(0);
                monthComboPremium.setSelectedIndex(0);
                yearComboPremium.setSelectedIndex(0);
                admissionDayComboPremium.setSelectedIndex(0);
                admissionMonthComboPremium.setSelectedIndex(0);
                admissionYearComboPremium.setSelectedIndex(0);
                genderGroupPremium.clearSelection();
                premiumChargeField.setText("");
                personalTrainerField.setText("");
           }
        });
        
        clearPremiumButton = new JButton("Clear");
        clearPremiumButton.setBounds(350, 220, 100, 30);
        styleButton(clearPremiumButton, red);
        premiumFormPanel.add(clearPremiumButton);
        clearPremiumButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                premiumIdField.setText("");
                premiumNameField.setText("");
                premiumAddressField.setText("");
                premiumPhoneField.setText("");
                premiumEmailField.setText("");
                dayComboPremium.setSelectedIndex(0);
                monthComboPremium.setSelectedIndex(0);
                yearComboPremium.setSelectedIndex(0);
                admissionDayComboPremium.setSelectedIndex(0);
                admissionMonthComboPremium.setSelectedIndex(0);
                admissionYearComboPremium.setSelectedIndex(0);
                genderGroupPremium.clearSelection();
                premiumChargeField.setText("");
                personalTrainerField.setText("");
            }
        });
        premiumFormPanel.setVisible(false);
    }
    
    // Method for easy set of labels
    private JLabel label(String text, int x, int y, Font font, Color color)
    {
        JLabel label = new JLabel(text);
        label.setFont(font);
        label.setForeground(color);
        label.setBounds(x, y, 100, 25);
        return label;
    }
    // Method to style button
    private void styleButton(JButton button, Color fg)
    {
        button.setBackground(Color.DARK_GRAY);
        button.setForeground(fg);
        button.setFocusPainted(false);
    }
    
    // Main method
    public static void main(String[] args)
    {
        new GymGUI();
    }
}