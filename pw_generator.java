import java.util.*;

public class AdvancedPasswordGenerator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of passwords to generate: ");
        int numberOfPasswords = scanner.nextInt();
        System.out.print("Enter the password length: ");
        int passwordLength = scanner.nextInt();
        System.out.print("Include special characters? (y/n): ");
        boolean includeSpecialCharacters = scanner.next().equalsIgnoreCase("y");
        System.out.print("Include numbers? (y/n): ");
        boolean includeNumbers = scanner.next().equalsIgnoreCase("y");

        List<String> generatedPasswords = generatePasswords(numberOfPasswords, passwordLength, includeSpecialCharacters, includeNumbers);
        System.out.println("Generated passwords:");
        for (String password : generatedPasswords) {
            System.out.println(password);
        }
    }

    private static List<String> generatePasswords(int numberOfPasswords, int length, boolean includeSpecialCharacters, boolean includeNumbers) {
        List<String> passwords = new ArrayList<>();
        for (int i = 0; i < numberOfPasswords; i++) {
            String password;
            do {
                password = generatePassword(length, includeSpecialCharacters, includeNumbers);
            } while (!isValidPassword(password, includeSpecialCharacters, includeNumbers));
            passwords.add(password);
        }
        return passwords;
    }

    private static String generatePassword(int length, boolean includeSpecialCharacters, boolean includeNumbers) {
        String lowercaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String uppercaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String specialCharacters = "!@#$%^&*()-_=+[{]}|;:',<.>/?";
        String numbers = "0123456789";

        StringBuilder passwordCharacters = new StringBuilder();
        passwordCharacters.append(lowercaseLetters).append(uppercaseLetters);

        if (includeSpecialCharacters) {
            passwordCharacters.append(specialCharacters);
        }

        if (includeNumbers) {
            passwordCharacters.append(numbers);
        }

        StringBuilder generatedPassword = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(passwordCharacters.length());
            generatedPassword.append(passwordCharacters.charAt(randomIndex));
        }

        return generatedPassword.toString();
    }

    private static boolean isValidPassword(String password, boolean includeSpecialCharacters, boolean includeNumbers) {
        boolean hasLowercase = false;
        boolean hasUppercase = false;
        boolean hasSpecialCharacter = false;
        boolean hasNumber = false;

        for (char c : password.toCharArray()) {
            if (Character.isLowerCase(c)) {
                hasLowercase = true;
            } else if (Character.isUpperCase(c)) {
                hasUppercase = true;
            } else if (includeSpecialCharacters && "!@#$%^&*()-_=+[{]}|;:',<.>/?".indexOf(c) >= 0) {
                hasSpecialCharacter = true;
            } else if (includeNumbers && Character.isDigit(c)) {
                hasNumber = true;
            }
        }

        return hasLowercase && hasUppercase && (!includeSpecialCharacters || hasSpecialCharacter) && (!includeNumbers || hasNumber);
    }
}
