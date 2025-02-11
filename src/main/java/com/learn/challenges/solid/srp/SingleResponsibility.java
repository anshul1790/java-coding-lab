package com.learn.challenges.solid.srp;

public class SingleResponsibility {

    // This class breaking the law of SRP
    static class PersonInvalid {
        String name;
        String email;

        public void saveUser() {

        }

        public void senEmail() {

        }

    }

    // This class breaking the law of SRP
    static class PersonValid {
        String name;
        String email;
    }

    static class UserRepository {
        public void saveUser() {

        }
    }

    static class EmailNotification {
        public void sendEmail() {

        }
    }

}
