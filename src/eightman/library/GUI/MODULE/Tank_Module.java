package eightman.library.GUI.MODULE;

public class Tank_Module extends Equipment_Module {
    private double reliability;
    private double maximum_speed;
    private double soft_attack;
    private double hard_attack;
    private double air_attack;
    private double ap_attack;
    private double breakthrough;
    private double defense;
    private double max_strength;
    private double armor_value;
    private double hardness;
    private double entrenchment;
    private double recon;
    private String forbid_equipment_type;
    private String[] can_convert_from_module_categorie;
    private double[] convert_cost_ic;

    public Tank_Module(String id,String name ,String content,String category, double lend_lease_cost, double build_cost_ic, int manpower, boolean can_license, boolean is_convertable,double xp_cost,
                       double reliability, double maximum_speed, double soft_attack, double hard_attack, double air_attack, double ap_attack,
                       double breakthrough, double defense, double max_strength, double armor_value, double hardness, double entrenchment, double recon, String forbid_equipment_type, String[] can_convert_from_module_categorie, double[] convert_cost_ic) {
        super(id,name, content, category,lend_lease_cost, build_cost_ic, manpower, can_license, is_convertable,xp_cost);
        this.reliability = reliability;
        this.maximum_speed = maximum_speed;
        this.soft_attack = soft_attack;
        this.hard_attack = hard_attack;
        this.air_attack = air_attack;
        this.ap_attack = ap_attack;
        this.breakthrough = breakthrough;
        this.defense = defense;
        this.max_strength = max_strength;
        this.armor_value = armor_value;
        this.hardness = hardness;
        this.entrenchment = entrenchment;
        this.recon = recon;
        this.forbid_equipment_type = forbid_equipment_type;
        this.can_convert_from_module_categorie = can_convert_from_module_categorie;
        this.convert_cost_ic = convert_cost_ic;

    }

    public double getReliability() {
        return reliability;
    }

    public double getMaximumSpeed() {
        return maximum_speed;
    }

    public double getSoftAttack() {
        return soft_attack;
    }

    public double getHardAttack() {
        return hard_attack;
    }

    public double getAirAttack() {
        return air_attack;
    }

    public double getApAttack() {
        return ap_attack;
    }

    public double getBreakthrough() {
        return breakthrough;
    }

    public double getDefense() {
        return defense;
    }

    public double getMaxStrength() {
        return max_strength;
    }

    public double getArmorValue() {
        return armor_value;
    }

    public double getHardness() {
        return hardness;
    }

    public double getEntrenchment() {
        return entrenchment;
    }

    public double getRecon() {
        return recon;
    }
    public String getForbidEquipmentType() {
        return forbid_equipment_type;
    }
}