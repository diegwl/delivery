package configs;

import entities.Status;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Objects;

public class BasicConfigs {
    String RESTS = "Restaurants.txt";
    String USERS = "Users.txt";

    public BasicConfigs(){

    }

    public ArrayList<String> getRests(){
        try{
            ArrayList<String> rests = new ArrayList<>();
            FileReader fr = new FileReader(RESTS);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                if (line.equals("")){
                    continue;
                }
                rests.add(line);
            }
            br.close();
            fr.close();
            return rests;
        } catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    public ArrayList<String> getUsers(){
        try{
            ArrayList<String> users = new ArrayList<>();
            FileReader fr = new FileReader(USERS);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                if (line.equals("")){
                    continue;
                }
                users.add(line);
            }
            br.close();
            fr.close();
            return users;
        } catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    public void addUser(String user, String cpf, int x, int y, Status status){
        try{
            FileWriter fw = new FileWriter(USERS, true);
            System.out.println(user+","+cpf+","+x+","+y+","+status+";\n");
            fw.write(user+","+cpf+","+x+","+y+","+status+";\n");
            fw.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void addRestaurant(String restaurant, int x, int y){
        try{
            FileWriter fw = new FileWriter(RESTS, true);
            fw.write(restaurant+","+x+","+y+"-\n");
            fw.close();
        } catch (Exception e){
            System.out.println(e);
        }
    }

    public void addFood(String restaurant, String food, float price){
        try{
            ArrayList<String> rests = getRests();
            ArrayList<String> updated = new ArrayList<>();
            for (String rest : rests){
                String r = rest.split("-")[0];
                String foods = "";
                try{
                    foods = rest.split("-")[1];
                }catch (ArrayIndexOutOfBoundsException ignored){

                }
//                System.out.println(r.split(",")[0]);
                if (Objects.equals(r.split(",")[0], restaurant)){
                    r = r + "-" + foods + food + "," + price + "F;";
                }else{
                    r = r + "-" + foods;
                }
                updated.add(r);
            }
            FileWriter clean = new FileWriter(RESTS);
            clean.write("");
            clean.flush();
            clean.close();
            FileWriter fw = new FileWriter(RESTS, true);
            for (String rest : updated) {
                fw.write(rest+"\n");
            }
            fw.close();

        } catch (Exception e){
            System.out.println(e);
        }
    }
}
