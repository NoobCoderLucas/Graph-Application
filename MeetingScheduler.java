import java.util.*;

public class MeetingScheduler {
    private Map<String, Meeting> meetingNetwork;

    public MeetingScheduler() {
        meetingNetwork = new HashMap<>();
    }

    public void addMeeting(String meetingName, String date, String time) {
        if (!meetingNetwork.containsKey(meetingName)) {
            meetingNetwork.put(meetingName, new Meeting(meetingName, date, time));
            System.out.println(meetingName + " scheduled on " + date + " at " + time + ".");
        } else {
            System.out.println(meetingName + " is already scheduled.");
        }
    }

    public void addAttendee(String meetingName, String attendee) {
        if (meetingNetwork.containsKey(meetingName)) {
            meetingNetwork.get(meetingName).addAttendee(attendee);
        } else {
            System.out.println(meetingName + " is not scheduled. Please schedule the meeting first.");
        }
    }

    public void removeAttendee(String meetingName, String attendee) {
        if (meetingNetwork.containsKey(meetingName)) {
            meetingNetwork.get(meetingName).removeAttendee(attendee);
        } else {
            System.out.println(meetingName + " is not scheduled.");
        }
    }

    public void showAttendees(String meetingName) {
        if (meetingNetwork.containsKey(meetingName)) {
            meetingNetwork.get(meetingName).showAttendees();
        } else {
            System.out.println(meetingName + " is not scheduled.");
        }
    }

    public void showAllMeetings() {
        if (meetingNetwork.isEmpty()) {
            System.out.println("No meetings are scheduled.");
        } else {
            System.out.println("Scheduled Meetings:");
            for (Meeting meeting : meetingNetwork.values()) {
                meeting.showMeetingDetails();
            }
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        MeetingScheduler scheduler = new MeetingScheduler();
        boolean running = true;

        while (running) {
            System.out.println("\n1. Schedule a Meeting");
            System.out.println("2. Add Attendee to a Meeting");
            System.out.println("3. Remove Attendee from a Meeting");
            System.out.println("4. Show Attendees for a Meeting");
            System.out.println("5. Show All Meetings");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scan.nextInt();
            scan.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Enter meeting name: ");
                    String meetingName = scan.nextLine();
                    System.out.print("Enter meeting date (YYYY-MM-DD): ");
                    String date = scan.nextLine();
                    System.out.print("Enter meeting time (HH:MM): ");
                    String time = scan.nextLine();
                    scheduler.addMeeting(meetingName, date, time);
                    break;
                case 2:
                    System.out.print("Enter meeting name: ");
                    String meetingToAdd = scan.nextLine();
                    System.out.print("Enter attendee name: ");
                    String attendeeToAdd = scan.nextLine();
                    scheduler.addAttendee(meetingToAdd, attendeeToAdd);
                    break;
                case 3:
                    System.out.print("Enter meeting name: ");
                    String meetingToRemove = scan.nextLine();
                    System.out.print("Enter attendee name: ");
                    String attendeeToRemove = scan.nextLine();
                    scheduler.removeAttendee(meetingToRemove, attendeeToRemove);
                    break;
                case 4:
                    System.out.print("Enter meeting name to view attendees: ");
                    String meetingToShow = scan.nextLine();
                    scheduler.showAttendees(meetingToShow);
                    break;
                case 5:
                    scheduler.showAllMeetings();
                    break;
                case 6:
                    running = false;
                    System.out.println("Exiting the application.");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scan.close();
    }

    // Inner class to represent a meeting
    class Meeting {
        private String meetingName;
        private String date;
        private String time;
        private Set<String> attendees;

        public Meeting(String meetingName, String date, String time) {
            this.meetingName = meetingName;
            this.date = date;
            this.time = time;
            this.attendees = new HashSet<>();
        }

        public void addAttendee(String attendee) {
            attendees.add(attendee);
            System.out.println(attendee + " added to " + meetingName + ".");
        }

        public void removeAttendee(String attendee) {
            if (attendees.remove(attendee)) {
                System.out.println(attendee + " removed from " + meetingName + ".");
            } else {
                System.out.println(attendee + " is not an attendee of this meeting.");
            }
        }

        public void showAttendees() {
            if (attendees.isEmpty()) {
                System.out.println("No attendees for " + meetingName + ".");
            } else {
                System.out.println("Attendees for " + meetingName + ": " + attendees);
            }
        }

        public void showMeetingDetails() {
            System.out.println("Meeting: " + meetingName + " | Date: " + date + " | Time: " + time);
        }
    }
}
