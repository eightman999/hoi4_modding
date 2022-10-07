package sunlight.library.inc.ship_name_type;
import java.io.*;
import sunlight.library.inc.ship_name_type.*;
import static sunlight.library.inc.Main.*;

public class SHIP_NAME_TYPES{
    public  void DESTROYER() {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        temp = temp + "###REGULAR DESTROYER NAMES###\n";
        temp = temp + Ctag + "_DD_HISTORICAL = {\n";
        temp = temp + "\tname = NAME_THEME_HISTORICAL_DESTROYERS\n";
        temp = temp + "\tfor_countries = { " + Ctag + " }\n\n";
        temp = temp + "\ttype = ship\n";
        DESTROYER_NAME.DD_NAME();

    }
    public  void LIGHT_CRUISER(){
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        temp = temp + "###LIGHT CRUISER NAMES###\n";
        temp = temp + Ctag +"_CL_HISTORICAL = {\n";
        temp = temp + "\tname = NAME_THEME_HISTORICAL_CL\n";
        temp = temp + "\tfor_countries = { " + Ctag + " }\n\n";
        temp = temp + "\ttype = ship\n";
       LIGHT_CRUISER_NAME.CL_NAME();
    }
    public  void HEAVY_CRUISER(){
        temp = temp + "###HEAVY CRUISER NAMES###\n";
        temp = temp + Ctag +"_CA_HISTORICAL = {\n";
        temp = temp + "\tname = NAME_THEME_HISTORICAL_CA\n";
        temp = temp + "\tfor_countries = { " + Ctag + " }\n\n";
        temp = temp + "\ttype = ship\n";
        HEAVY_CRUISER_NAME.CA_NAME();
    }
    public  void BATTLESHIP(){
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        temp = temp + "###BATTLESHIP NAMES###\n";
        temp = temp + Ctag +"_BB_HISTORICAL = {\n";
        temp = temp + "\tname = NAME_THEME_HISTORICAL_BB\n";
        temp = temp + "\tfor_countries = { " + Ctag + " }\n\n";
        temp = temp + "\ttype = ship\n";
        BATTLESHIP_NAME.BB_NAME();
    }
    public  void BATTLE_CRUISER(){
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        temp = temp + "###BATTLECRUISER NAMES###\n";
        temp = temp + Ctag +"_BC_HISTORICAL = {\n";
        temp = temp + "\tname = NAME_THEME_HISTORICAL_BC\n";
        temp = temp + "\tfor_countries = { " + Ctag + " }\n\n";
        temp = temp + "\ttype = ship\n";
        BATTLE_CRUISER_NAME.BC_NAME();
    }
    public  void AIRCRAFT_CARRIER(){
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        temp = temp + "###AIRCRAFT CARRIER NAMES###\n";
        temp = temp + Ctag +"_CV_HISTORICAL = {\n";
        temp = temp + "\tname = NAME_THEME_HISTORICAL_CV\n";
        temp = temp + "\tfor_countries = { " + Ctag + " }\n\n";
        temp = temp + "\ttype = ship\n";
        AIRCRAFT_CARRIER_NAME.CV_NAME();
    }
    public  void LIGHT_CARRIER(){
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        temp = temp + "###LIGHT CARRIER NAMES###\n";
        temp = temp + Ctag +"_CVL_HISTORICAL = {\n";
        temp = temp + "\tname = NAME_THEME_HISTORICAL_CVL\n";
        temp = temp + "\tfor_countries = { " + Ctag + " }\n\n";
        temp = temp + "\ttype = ship\n";
        AIRCRAFT_CARRIER_NAME.CV_NAME();
    }
    public  void HEAVY_SUBMAIRNE(){
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        temp = temp + "###HEAVY SUBMARINE NAMES###\n";
        temp = temp + Ctag +"_CS_HISTORICAL = {\n";
        temp = temp + "\tname = NAME_THEME_HISTORICAL_CS\n";
        temp = temp + "\tfor_countries = { " + Ctag + " }\n\n";
        temp = temp + "\ttype = ship\n";
        HEAVY_SUBMARINE.HS_NAME();
    }
    public void SUBMARINE(){
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        temp = temp + "###SUBMARINE NAMES###\n";
        temp = temp + Ctag +"_SS_HISTORICAL = {\n";
        temp = temp + "\tname = NAME_THEME_HISTORICAL_SS\n";
        temp = temp + "\tfor_countries = { " + Ctag + " }\n\n";
        temp = temp + "\ttype = ship\n";
        SUBMARINE_NAME.SS_NAME();
    }
    public void LIGHT_SUBMARINE(){
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        temp = temp + "###LIGHT SUBMARINE NAMES###\n";
        temp = temp + Ctag +"_MS_HISTORICAL = {\n";
        temp = temp + "\tname = NAME_THEME_HISTORICAL_MS\n";
        temp = temp + "\tfor_countries = { " + Ctag + " }\n\n";
        temp = temp + "\ttype = ship\n";
        LIGHT_SUBMARINE_NAME.MS_NAME();
    }
    public void CITIES (){
        temp = temp + "###CITIES###\n";
        temp = temp + Ctag +"_CITIES = {\n";
        temp = temp + "\tname = NAME_THEME_CITIES\n";
        temp = temp + "\tfor_countries = { " + Ctag + " }\n\n";
        temp = temp + "\ttype = ship\n";
        temp = temp + "unique = {\n";
        CITY_NAME.CITIES();
    }
    public void MOUNTAINS(){
        temp = temp + "###MOUNTAIN###\n";
        temp = temp + Ctag +"_MOUNTAIN = {\n";
        temp = temp + "\tname = NAME_THEME_MOUNTAIN\n";
        temp = temp + "\tfor_countries = { " + Ctag + " }\n\n";
        temp = temp + "\ttype = ship\n";
        temp = temp + "unique = {\n";
        MOUNTAIN_NAME.MOUNTAIN();
    }
    public void RIVERS(){
        temp = temp + "###RIVER###\n";
        temp = temp + Ctag +"_RIVER = {\n";
        temp = temp + "\tname = NAME_THEME_RIVER\n";
        temp = temp + "\tfor_countries = { " + Ctag + " }\n\n";
        temp = temp + "\ttype = ship\n";
        temp = temp + "unique = {\n";
        RIVER_NAME.RIVERS();
    }
    public void PLANTS(){
        temp = temp + "###PLANTS###\n";
        temp = temp + Ctag +"PLANTS = {\n";
        temp = temp + "\tname = NAME_THEME_PLANTS\n";
        temp = temp + "\tfor_countries = { " + Ctag + " }\n\n";
        temp = temp + "\ttype = ship\n";
        temp = temp + "unique = {\n";
        PLANTS_NAME.PLANTS();
    }
    public void PREFECTURES(){
        temp = temp + "###PREFECTURES###\n";
        temp = temp + Ctag +"_PREFECTURES = {\n";
        temp = temp + "\tname = NAME_THEME_PREFECTURES\n";
        temp = temp + "\tfor_countries = { " + Ctag + " }\n\n";
        temp = temp + "\ttype = ship\n";
        temp = temp + "unique = {\n";
        PREFECTURES_NAME.PREFECTURES();
    }
    public void ISLANDS(){
        temp = temp + "###ISLANDS###\n";
        temp = temp + Ctag +"_ISLANDS = {\n";
        temp = temp + "\tname = NAME_THEME_ISLANDS\n";
        temp = temp + "\tfor_countries = { " + Ctag + " }\n\n";
        temp = temp + "\ttype = ship\n";
        temp = temp + "unique = {\n";
        ISLANDS_NAME.ISLANDS();
    }
    public void STRAIT(){
        temp = temp + "###STRAIT###\n";
        temp = temp + Ctag +"_STRAIT = {\n";
        temp = temp + "\tname = NAME_THEME_STRAIT\n";
        temp = temp + "\tfor_countries = { " + Ctag + " }\n\n";
        temp = temp + "\ttype = ship\n";
        temp = temp + "unique = {\n";
        STRAIT_NAME.STRAIT();
    }
}
