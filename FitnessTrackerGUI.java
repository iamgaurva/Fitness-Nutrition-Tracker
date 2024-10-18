import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
public class FitnessTrackerGUI extends JFrame {
    private ArrayList<User> users = new ArrayList<>();
    private User currentUser ;
    private ArrayList<Progress> progressList = new ArrayList<>();
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField nameField;
    private JTextField ageField;
    private JComboBox<String> genderField;
    private JTextField weightField;
    private JTextField heightField;
    private JTextField stepsField;
    private JTextField heartRateField;
    private JTextField bloodPressureField;
    private JCheckBox vegetarianField;
    private JTextArea outputArea;
    public FitnessTrackerGUI() {
        setTitle("TRACK YOUR TRIUMPH");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        JLabel titleLabel = new JLabel("TRACK YOUR TRIUMPH");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titlePanel.add(titleLabel);
        JLabel subtitleLabel = new JLabel("Fitness and Nutrition Tracker");
        subtitleLabel.setFont(new Font("Arial", Font.ITALIC, 16));
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titlePanel.add(subtitleLabel);
        add(titlePanel, BorderLayout.NORTH);
        JPanel mainPanel = new JPanel(new GridLayout(1, 2));
        JPanel inputPanel = new JPanel(new GridLayout(12, 2));
        inputPanel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        inputPanel.add(usernameField);
        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Age:"));
        ageField = new JTextField();
        inputPanel.add(ageField);
        inputPanel.add(new JLabel("Gender:"));
        String[] genders = {"Male", "Female", "Others"}; 
        genderField = new JComboBox<>(genders);
        inputPanel.add(genderField);
        inputPanel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        inputPanel.add(passwordField);
        inputPanel.add(new JLabel("Weight (kg):"));
        weightField = new JTextField();
        inputPanel.add(weightField);
        inputPanel.add(new JLabel("Height (ft):"));
        heightField = new JTextField();
        inputPanel.add(heightField);
        inputPanel.add(new JLabel("Weekly Steps:"));
        stepsField = new JTextField();
        inputPanel.add(stepsField);
        inputPanel.add(new JLabel("Heart Rate (bpm):"));
        heartRateField = new JTextField();
        inputPanel.add(heartRateField);
        inputPanel.add(new JLabel("Blood Pressure (mmHg):"));
        bloodPressureField = new JTextField();
        inputPanel.add(bloodPressureField);
        vegetarianField = new JCheckBox("Vegetarian");
        inputPanel.add(vegetarianField);
        createButton(inputPanel, "Create Profile", new CreateProfileListener(), new Color(0, 153, 0)); 
        createButton(inputPanel, "Login", new LoginListener(), new Color(0, 102, 204));
        createButton(inputPanel, "Health & Fitness Metrics", new HealthFitnessMetricsListener(), new Color(255, 165, 0)); 
        createButton(inputPanel, "Fitness Goals", new FitnessGoalsListener(), new Color(255, 69, 0)); 
        createButton(inputPanel, "View Diet Plan", new ViewDietPlanListener(), new Color(34, 139, 34)); 
        createButton(inputPanel, "View Exercise Plan", new ViewExercisePlanListener(), new Color(30, 144, 255)); 
        createButton(inputPanel, "Current Stats", new CurrentStatsListener(), new Color(255, 215, 0)); 
        createButton(inputPanel, "Input Progress", new InputProgressListener(), new Color(255, 105, 97)); 
        createButton(inputPanel, "View Progress", new ViewProgressListener(), new Color(0, 128, 0));
        createButton(inputPanel, "Update Profile", new UpdateProfileListener(), new Color(128, 0, 128)); 
        createButton(inputPanel, "Delete Profile", new DeleteProfileListener(), new Color(255, 0, 0));
        createButton(inputPanel, "Exit", new ExitListener(), new Color(0, 0, 0));
        mainPanel.add(inputPanel);
        JPanel outputPanel = new JPanel(new BorderLayout());
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputPanel.add(new JScrollPane(outputArea), BorderLayout.CENTER);
        mainPanel.add(outputPanel);
        add(mainPanel, BorderLayout.CENTER);
        setVisible(true);
    }
    private void createButton(JPanel panel, String text, ActionListener listener, Color color) {
        JButton button = new JButton(text);
        button.addActionListener(listener);
        button.setBackground(color);
        button.setForeground(Color.WHITE); 
        panel.add(button);
    }
    private class LoginListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            currentUser = findUser (username, password);
            if (currentUser != null) {
                outputArea.setText("Logged in as " + currentUser.getName());
            } else {
                outputArea.setText("Invalid username or password.");
            }
        }
    }
    private class HealthFitnessMetricsListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (currentUser == null) {
                outputArea.setText("Please log in to view your health and fitness metrics.");
                return;
            }
            String metrics = "Name: " + currentUser.getName() + "\n" +
                    "Age: " + currentUser.getAge() + "\n" +
                    "Gender: " + currentUser.getGender() + "\n" +
                    "Weight: " + currentUser.getWeight() + " kg\n" +
                    "Height: " + currentUser.getHeight() + " ft\n" +
                    "Weekly Steps: " + currentUser.getWeeklySteps() + "\n" +
                    "Heart Rate: " + currentUser.getHeartRate() + " bpm\n" +
                    "Blood Pressure: " + currentUser.getBloodPressure() + " mmHg\n" +
                    "Vegetarian: " + currentUser.isVegetarian();
            outputArea.setText("Health and Fitness Metrics:\n" + metrics);
        }
    }
    private class FitnessGoalsListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (currentUser == null) {
                outputArea.setText("Please log in to set your fitness goal.");
                return;
            }
            String fitnessGoal = JOptionPane.showInputDialog("Enter your fitness goal (Gain Weight, Moderate Weight, Lose Weight):");
            if (fitnessGoal != null && !fitnessGoal.trim().isEmpty()) {
                currentUser.setFitnessGoal(fitnessGoal);
                System.out.println("Fitness goal set to: " + fitnessGoal); 
                outputArea.setText("Fitness goal set to: " + fitnessGoal);
            } else {
                outputArea.setText("Invalid fitness goal. Please enter a valid goal.");
            }
        }
    }
    private class ViewDietPlanListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (currentUser == null) {
                outputArea.setText("Please log in to view your diet plan.");
                return;
            }
            if (currentUser.getFitnessGoal() == null || currentUser.getFitnessGoal().isEmpty()) {
                outputArea.setText("Please set your fitness goal before viewing the diet plan.");
                return;
            }
            String dietPlan = currentUser.getDietPlanBasedOnGoal();
            outputArea.setText("Diet Plan:\n" + dietPlan);
        }
    }
    private class ViewExercisePlanListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (currentUser == null) {
                outputArea.setText("Please log in to view your exercise plan.");
                return;
            }
            if (currentUser.getFitnessGoal() == null || currentUser.getFitnessGoal().isEmpty()) {
                outputArea.setText("Please set your fitness goal before viewing the exercise plan.");
                return;
            }
            String exercisePlan = currentUser.getExercisePlanBasedOnGoal();
            outputArea.setText("Exercise Plan:\n" + exercisePlan);
        }
    }
    private class CreateProfileListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String name = nameField.getText();
                int age = Integer.parseInt(ageField.getText());
                String gender = (String) genderField.getSelectedItem();
                double weight = Double.parseDouble(weightField.getText());
                double height = Double.parseDouble(heightField.getText());
                int weeklySteps = Integer .parseInt(stepsField.getText());
                int heartRate = Integer.parseInt(heartRateField.getText());
                String bloodPressure = bloodPressureField.getText();
                boolean isVegetarian = vegetarianField.isSelected();
                User user = new User(username, password, name, age, gender, weight, height, weeklySteps, heartRate, bloodPressure, isVegetarian);
                users.add(user);
                outputArea.setText("Profile created successfully!");
            } catch (NumberFormatException ex) {
                outputArea.setText("Invalid input. Please enter valid numbers.");
            }
        }
    }
    private class CurrentStatsListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (currentUser == null) {
                outputArea.setText("Please log in to view your current stats.");
                return;
            }
            String stats = "Name: " + currentUser.getName() + "\n" +
                    "Age: " + currentUser.getAge() + "\n" +
                    "Gender: " + currentUser.getGender() + "\n" +
                    "Weight: " + currentUser.getWeight() + " kg\n" +
                    "Height: " + currentUser.getHeight() + " ft\n" +
                    "Weekly Steps: " + currentUser.getWeeklySteps() + "\n" +
                    "Heart Rate: " + currentUser.getHeartRate() + " bpm\n" +
                    "Blood Pressure: " + currentUser.getBloodPressure() + " mmHg\n" +
                    "Vegetarian: " + currentUser.isVegetarian() + "\n" +
                    "Fitness Goal: " + currentUser.getFitnessGoal();
            outputArea.setText("Current Stats:\n" + stats);
        }
    }
    private class InputProgressListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (currentUser == null) {
                outputArea.setText("Please log in to input your progress.");
                return;
            }
            try {
                String date = JOptionPane.showInputDialog("Enter the date (dd/mm/yyyy):");
                double height = Double.parseDouble(JOptionPane.showInputDialog("Enter your height (ft):"));
                double weight = Double.parseDouble(JOptionPane.showInputDialog("Enter your weight (kg):"));
                String bloodPressure = JOptionPane.showInputDialog("Enter your blood pressure (mmHg):");
                int heartRate = Integer.parseInt(JOptionPane.showInputDialog("Enter your heart rate (bpm):"));
                int steps = Integer.parseInt(JOptionPane.showInputDialog("Enter your steps:"));
                boolean dietFollowed = JOptionPane.showConfirmDialog(null, "Did you follow your diet plan?") == JOptionPane.YES_OPTION;
                boolean exerciseDone = JOptionPane.showConfirmDialog(null, "Did you complete your exercise plan?") == JOptionPane.YES_OPTION;
                int calories = Integer.parseInt(JOptionPane.showInputDialog("Enter your calories intake:"));
                Progress progress = new Progress(date, height, weight, bloodPressure, heartRate, steps, dietFollowed, exerciseDone, calories);
                progressList.add(progress);
                outputArea.setText("Day's progress saved successfully!");
            } catch (NumberFormatException ex) {
                outputArea.setText("Invalid input. Please enter valid numbers.");
            }
        }
    }
    private class ViewProgressListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (currentUser == null) {
                outputArea.setText("Please log in to view your progress.");
                return;
            }
            StringBuilder progressListString = new StringBuilder();
            for (Progress progress : progressList) {
                progressListString.append("Date: ").append(progress.getDate()).append("\n");
                progressListString.append("Height: ").append(progress.getHeight()).append(" ft\n");
                progressListString.append("Weight: ").append(progress.getWeight()).append(" kg\n");
                progressListString.append("Blood Pressure: ").append(progress.getBloodPressure()).append(" mmHg\n");
                progressListString.append("Heart Rate: ").append(progress.getHeartRate()).append(" bpm\n");
                progressListString.append("Steps: ").append(progress.getSteps()).append("\n");
                progressListString.append("Diet Followed: ").append(progress.getDietFollowed()).append("\n");
                progressListString.append("Exercise Done: ").append(progress.getExerciseDone()).append("\n");
                progressListString.append("Calories Intake: ").append(progress.getCalories()).append("cal\n\n");
            }
            outputArea.setText("Progress List:\n" + progressListString.toString());
        }
    }
    private class UpdateProfileListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (currentUser == null) {
                outputArea.setText("Please log in to update your profile.");
                return;
            }
            try {
                String name = JOptionPane.showInputDialog("Enter your new name:");
                int age = Integer.parseInt(JOptionPane.showInputDialog("Enter your new age:"));
                String gender = JOptionPane.showInputDialog("Enter your new gender (Male/Female/Others):");
                String password = JOptionPane.showInputDialog("Enter your new password:");
                currentUser.updateProfile(name , age, gender, password);
                outputArea.setText("Profile updated successfully!");
            } catch (NumberFormatException ex) {
                outputArea.setText("Invalid input. Please enter valid numbers.");
            }
        }
    }
    private class DeleteProfileListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (currentUser == null) {
                outputArea.setText("Please log in to delete your profile.");
                return;
            }
            users.remove(currentUser);
            currentUser = null;
            outputArea.setText("Profile deleted successfully!");
        }
    }
    private class ExitListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
    private User findUser(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
    public static void main(String[] args) {
        new FitnessTrackerGUI();
    }
}
class User {
    private String username;
    private String password;
    private String name;
    private int age;
    private String gender;
    private double weight;
    private double height;
    private int weeklySteps;
    private int heartRate;
    private String bloodPressure;
    private boolean isVegetarian;
    private String fitnessGoal;
    public User(String username, String password, String name, int age, String gender, double weight, double height, int weeklySteps, int heartRate, String bloodPressure, boolean isVegetarian) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.weight = weight;
        this.height = height;
        this.weeklySteps = weeklySteps;
        this.heartRate = heartRate;
        this.bloodPressure = bloodPressure;
        this.isVegetarian = isVegetarian;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
       }
    public double getWeight() {
        return weight;
    }
    public void setWeight(double weight) {
        this.weight = weight;
    }
    public double getHeight() {
        return height;
    }
    public void setHeight(double height) {
        this.height = height;
    }
    public int getWeeklySteps() {
        return weeklySteps;
    }
    public void setWeeklySteps(int weeklySteps) {
        this.weeklySteps = weeklySteps;
    }
    public int getHeartRate() {
        return heartRate;
      }
    public void setHeartRate(int heartRate) {
        this.heartRate = heartRate;
    }
    public String getBloodPressure() {
        return bloodPressure;
    }
    public void setBloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure;
    }
    public boolean isVegetarian() {
        return isVegetarian;
    }
    public void setVegetarian(boolean vegetarian) {
        isVegetarian = vegetarian;
    }
    public String getFitnessGoal() {
        return fitnessGoal;
    }
    public void setFitnessGoal(String fitnessGoal) {
        this.fitnessGoal = fitnessGoal;
    }
    public String getDietPlanBasedOnGoal() {
        double weight = this.weight;
        String plan = "";
        if (fitnessGoal.equalsIgnoreCase("Gain Weight")) { 
            if (isVegetarian) {
                plan = "Diet Plan for Gaining Weight (Vegetarian):\n" +
                        "Monday: Oatmeal with fruits and nuts\n" +
                        "Tuesday: Whole-grain toast with avocado and eggs\n" +
                        "Wednesday: Quinoa salad with chickpeas and vegetables\n" +
                        "Thursday: Lentil soup with whole-grain bread \n" +
                        "Friday: Grilled tofu with roasted vegetables and brown rice\n" +
                        "Saturday: Smoothie bowl with banana, spinach, and almond milk\n" +
                        "Sunday: Vegetable stir-fry with tofu and brown rice\n";
            } else {
                plan = "Diet Plan for Gaining Weight:\n" +
                        "Monday: Oatmeal with fruits and nuts\n" +
                        "Tuesday: Whole-grain toast with avocado and eggs\n" +
                        "Wednesday: Chicken stir-fry with vegetables and brown rice\n" +
                        "Thursday: Grilled salmon with quinoa and steamed vegetables\n" +
                        "Friday: Beef stir-fry with vegetables and brown rice\n" +
                        "Saturday: Smoothie bowl with banana, spinach, and almond milk\n" +
                        "Sunday: Grilled chicken with roasted vegetables and sweet potatoes\n";
            }
        } else if (fitnessGoal.equalsIgnoreCase("Moderate Weight")) { 
            if (isVegetarian) {
                plan = "Diet Plan for Maintaining Weight (Vegetarian):\n" +
                        "Monday: Oatmeal with fruits and nuts\n" +
                        "Tuesday: Whole-grain toast with avocado and eggs\n" +
                        "Wednesday: Quinoa salad with chickpeas and vegetables\n" +
                        "Thursday: Lentil soup with whole-grain bread \n" +
                        "Friday: Grilled tofu with roasted vegetables and brown rice\n" +
                        "Saturday: Smoothie bowl with banana, spinach, and almond milk\n" +
                        "Sunday: Vegetable stir-fry with tofu and brown rice\n";
            } else {
                plan = "Diet Plan for Maintaining Weight:\n" +
                        "Monday: Oatmeal with fruits and nuts\n" +
                        "Tuesday: Whole-grain toast with avocado and eggs\n" +
                        "Wednesday: Chicken stir-fry with vegetables and brown rice\n" +
                        "Thursday: Grilled salmon with quinoa and steamed vegetables\n" +
                        "Friday: Beef stir-fry with vegetables and brown rice\n" +
                        "Saturday: Smoothie bowl with banana, spinach, and almond milk\n" +
                        "Sunday: Grilled chicken with roasted vegetables and sweet potatoes\n";
            }
        } else if (fitnessGoal.equalsIgnoreCase("Lose Weight")) { 
            if (isVegetarian) {
                plan = "Diet Plan for Losing Weight (Vegetarian):\n" +
                        "Monday: Oatmeal with fruits and nuts\n" +
                        "Tuesday: Whole-grain toast with avocado and eggs\n" +
                        "Wednesday: Quinoa salad with chickpeas and vegetables\n" +
                        "Thursday: Lentil soup with whole-grain bread \n" +
                        "Friday: Grilled tofu with roasted vegetables and brown rice\n" +
                        "Saturday: Smoothie bowl with banana, spinach, and almond milk\n" +
                        "Sunday: Vegetable stir-fry with tofu and brown rice\n";
            } else {
                plan = "Diet Plan for Losing Weight:\n" +
                        "Monday: Oatmeal with fruits and nuts\n" +
                        "Tuesday: Whole-grain toast with avocado and eggs\n" +
                        "Wednesday: Chicken stir-fry with vegetables and brown rice\n" +
                        "Thursday: Grilled salmon with quinoa and steamed vegetables\n" +
                        "Friday: Beef stir-fry with vegetables and brown rice\n" +
                        "Saturday: Smoothie bowl with banana, spinach, and almond milk\n" +
                        "Sunday: Grilled chicken with roasted vegetables and sweet potatoes\n";
            }
        }
        return plan;
    }
    public String getExercisePlanBasedOnGoal() {
        String plan = "";
        if (fitnessGoal.equalsIgnoreCase("Gain Weight")) { 
            plan = "Exercise Plan for Gaining Weight:\n" +
                    "Monday: Strength training (legs and core)\n" +
                    "Tuesday: Cardio (30 minutes of moderate-intensity exercise)\n" +
                    "Wednesday: Strength training (upper body)\n" +
                    "Thursday: Rest day\n" +
                    "Friday: Strength training (full body)\n" +
                    "Saturday: Cardio (30 minutes of high-intensity exercise)\n" +
                    "Sunday: Rest day\n";
        } else if (fitnessGoal.equalsIgnoreCase("Moderate Weight")) {
            plan = "Exercise Plan for Maintaining Weight:\n" +
                    "Monday: Cardio (30 minutes of moderate-intensity exercise)\n" +
                    "Tuesday: Strength training (upper body)\n" +
                    "Wednesday: Cardio (30 minutes of high-intensity exercise)\n" +
                    "Thursday: Rest day\n" +
                    "Friday: Strength training (full body)\n" +
                    "Saturday: Cardio (30 minutes of moderate-intensity exercise)\n" +
                    "Sunday: Rest day\n";
        } else if (fitnessGoal.equalsIgnoreCase("Lose Weight")) { 
            plan = "Exercise Plan for Losing Weight:\n" +
                    "Monday: Cardio (30 minutes of high-intensity exercise)\n" +
                    "Tuesday: Strength training (upper body)\n" +
                    "Wednesday: Cardio (30 minutes of moderate-intensity exercise)\n" +
                    "Thursday: Rest day\n" +
                    "Friday: Strength training (full body)\n" +
                    "Saturday: Cardio (30 minutes of high-intensity exercise)\n" +
                    "Sunday: Rest day\n";
        }
        return plan;
    }
    public void updateProfile(String name, int age, String gender, String password) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.password = password;
    }
}
class Progress {
    private String date;
    private double height;
    private double weight;
    private String bloodPressure;
    private int heartRate;
    private int steps;
    private boolean dietFollowed;
    private boolean exerciseDone;
    private int calories;
    public Progress(String date, double height, double weight, String bloodPressure, int heartRate, int steps, boolean dietFollowed, boolean exerciseDone, int calories) {
        this.date = date;
        this.height = height;
        this.weight = weight;
        this.bloodPressure = bloodPressure;
        this.heartRate = heartRate;
        this.steps = steps;
        this.dietFollowed = dietFollowed;
        this.exerciseDone = exerciseDone;
        this.calories = calories;
    }
    public String getDate() {
        return date;
    }
    public double getHeight() {
        return height;
    }
    public double getWeight() {
        return weight;
    }
    public String getBloodPressure() {
        return bloodPressure;
    }
    public int getHeartRate() {
        return heartRate;
    }
    public int getSteps() {
        return steps;
    }
    public boolean getDietFollowed() {
        return dietFollowed;
    }
    public boolean getExerciseDone() {
        return exerciseDone;
    }
    public int getCalories() {
        return calories;
    }
}
