package application.services;

import Domain.Entities.User;
import application.services.dto.UserCodeDTO;
import java.util.Collection;
import java.util.ArrayList;

public class UsersCode {
    private final Collection<UserCodeDTO> usersCode = new ArrayList<>();

    public String generateCode() {
        int code = (int) (Math.random() * 999999);
        return formatCode(code);
    }

    private String formatCode(int code) {
        String codeString = Integer.toString(code);
        String finalCode = codeString;

        if (codeString.length() < 6) {
            finalCode = "0" + codeString;
        }
        if (codeString.length() < 5) {
            finalCode = "00" + codeString;
        }
        if (codeString.length() < 4) {
            finalCode = "000" + codeString;
        }
        if (codeString.length() < 3) {
            finalCode = "0000" + codeString;
        }
        if (codeString.length() < 2) {
            finalCode = "00000" + codeString;
        }
        return finalCode;
    }

    public void save(UserCodeDTO userCode) {
        UserCodeDTO userCodeSeek = this.findByEmail(userCode.user.email);
        if (userCodeSeek != null) {
            this.usersCode.remove(userCodeSeek);
        }
        this.usersCode.add(userCode);
    }

    public UserCodeDTO findByEmail(String email) {
        UserCodeDTO userCodeSeek = null;

        for (UserCodeDTO userCode : this.usersCode) {
            if (userCode.user.email.equals(email)) {
                userCodeSeek = userCode;
                break;
            }
        }

        return userCodeSeek;
    }

    public void delete(String email) {
        UserCodeDTO userCode = this.findByEmail(email);
        if (userCode != null) {
            this.usersCode.remove(userCode);
        }
    }
}
