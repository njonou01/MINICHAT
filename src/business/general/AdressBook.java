package business.general;

import java.util.ArrayList;

public class AdressBook {
    private String fileName;
    ArrayList<String> listContact;

    public AdressBook(String fileName) {
        this.fileName = fileName;
    }

    public ArrayList<String> getAllContacts() {
        return listContact;
    }

    public void addAdress(String ip) {
        if (isIP(ip) && verifIfIpExist(ip))
            listContact.add(ip);
        else
            throw new IllegalArgumentException("Entrer une adresse IP valide");
    }

    public void removeAdress(String adress) {
        listContact.remove(adress);
    }

    private boolean isIP(String IP) {
        String[] ipArray = IP.split(".");
        if (ipArray.length == 4) {
            try {
                for (String ipPart : ipArray) {
                    if (Integer.parseInt(ipPart) < 0 || Integer.parseInt(ipPart) > 255)
                        return false;
                }
            } catch (Exception e) {
                return false;
            }

            return true;
        }
        return false;
    }

    private boolean verifIfIpExist(String ip) {
        for (String contact : listContact)
            if (!contact.equals(ip))
                return false;
        return true;
    }

}
