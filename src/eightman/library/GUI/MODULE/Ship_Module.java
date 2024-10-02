package eightman.library.GUI.MODULE;

public class Ship_Module extends Equipment_Module{
    private double naval_speed;
    private double fire_range;
    private double lg_armor_piercing;
    private double lg_attack;
    private double hg_armor_piercing;
    private double hg_attack;
    private double anti_air_attack;
    private double shore_bombardment;
    private double evasion;
    private double surface_detection;
    private double sub_attack;
    private double sub_detection;
    private double surface_visibility;
    private double sub_visibility;
    private double naval_range;
    private double port_capacity_usage;
    private double search_and_destroy_coordination;
    private double convoy_raiding_coordination;
    private String[] critical_parts;
    private String[] can_convert_from_module_categorie;
    private double[] convert_cost_ic;

    public Ship_Module(String id,String name, String content, String category , double lend_lease_cost, double build_cost_ic, int manpower, boolean can_license, boolean is_convertable,double xp_cost,
                       double naval_speed, double fire_range, double lg_armor_piercing, double lg_attack, double hg_armor_piercing, double hg_attack,
                       double anti_air_attack, double shore_bombardment, double evasion, double surface_detection, double sub_attack, double sub_detection,
                       double surface_visibility, double sub_visibility, double naval_range, double port_capacity_usage, double search_and_destroy_coordination,
                       double convoy_raiding_coordination, String[] critical_parts, String[] can_convert_from_module_categorie, double[] convert_cost_ic) {
        super(id,name, content, category, lend_lease_cost, build_cost_ic, manpower, can_license, is_convertable,xp_cost);
        this.naval_speed = naval_speed;
        this.fire_range = fire_range;
        this.lg_armor_piercing = lg_armor_piercing;
        this.lg_attack = lg_attack;
        this.hg_armor_piercing = hg_armor_piercing;
        this.hg_attack = hg_attack;
        this.anti_air_attack = anti_air_attack;
        this.shore_bombardment = shore_bombardment;
        this.evasion = evasion;
        this.surface_detection = surface_detection;
        this.sub_attack = sub_attack;
        this.sub_detection = sub_detection;
        this.surface_visibility = surface_visibility;
        this.sub_visibility = sub_visibility;
        this.naval_range = naval_range;
        this.port_capacity_usage = port_capacity_usage;
        this.search_and_destroy_coordination = search_and_destroy_coordination;
        this.convoy_raiding_coordination = convoy_raiding_coordination;
        this.critical_parts = critical_parts;
        this.can_convert_from_module_categorie = can_convert_from_module_categorie;
        this.convert_cost_ic = convert_cost_ic;
    }
    public double getNavalSpeed() {
        return naval_speed;
    }

    public double getFireRange() {
        return fire_range;
    }

    public double getLgArmorPiercing() {
        return lg_armor_piercing;
    }

    public double getLgAttack() {
        return lg_attack;
    }

    public double getHgArmorPiercing() {
        return hg_armor_piercing;
    }

    public double getHgAttack() {
        return hg_attack;
    }

    public double getAntiAirAttack() {
        return anti_air_attack;
    }

    public double getShoreBombardment() {
        return shore_bombardment;
    }

    public double getEvasion() {
        return evasion;
    }

    public double getSurfaceDetection() {
        return surface_detection;
    }

    public double getSubAttack() {
        return sub_attack;
    }

    public double getSubDetection() {
        return sub_detection;
    }

    public double getSurfaceVisibility() {
        return surface_visibility;
    }

    public double getSubVisibility() {
        return sub_visibility;
    }

    public double getNavalRange() {
        return naval_range;
    }

    public double getPortCapacityUsage() {
        return port_capacity_usage;
    }

    public double getSearchAndDestroyCoordination() {
        return search_and_destroy_coordination;
    }

    public double getConvoyRaidingCoordination() {
        return convoy_raiding_coordination;
    }
    public String[] getCriticalParts() {
        return critical_parts;
    }
    public String[] getCanConvertFromModuleCategorie() {
        return can_convert_from_module_categorie;
    }
    public double[] getConvertCostIc() {
        return convert_cost_ic;
    }
}
