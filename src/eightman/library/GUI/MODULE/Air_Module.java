package eightman.library.GUI.MODULE;

public class Air_Module extends Equipment_Module {
    private double air_attack;
    private double air_defence;
    private double air_range;
    private double air_agility;
    private double air_ground_attack;
    private double air_bombing;
    private double air_superiority;
    private double naval_strike_attack;
    private double naval_strike_targetting;
    private double carrier_size;
    private double default_carrier_composition_weight;
    private boolean carrier_capable;
    private String[] can_convert_from_module_categorie;
    private double[] convert_cost_ic;

    public Air_Module(String id,String name, String content,String category, double lend_lease_cost, double build_cost_ic, int manpower, boolean can_license, boolean is_convertable,double xp_cost,
                      double air_attack, double air_defence, double air_range, double air_agility, double air_ground_attack, double air_bombing,
                      double air_superiority, double naval_strike_attack, double naval_strike_targetting, double carrier_size,
                      double default_carrier_composition_weight, boolean carrier_capable, String[] can_convert_from_module_categorie, double[] convert_cost_ic) {
        super(id, name, content, category,lend_lease_cost, build_cost_ic, manpower, can_license, is_convertable,xp_cost);
        this.air_attack = air_attack;
        this.air_defence = air_defence;
        this.air_range = air_range;
        this.air_agility = air_agility;
        this.air_ground_attack = air_ground_attack;
        this.air_bombing = air_bombing;
        this.air_superiority = air_superiority;
        this.naval_strike_attack = naval_strike_attack;
        this.naval_strike_targetting = naval_strike_targetting;
        this.carrier_size = carrier_size;
        this.default_carrier_composition_weight = default_carrier_composition_weight;
        this.carrier_capable = carrier_capable;
        this.can_convert_from_module_categorie = can_convert_from_module_categorie;
        this.convert_cost_ic = convert_cost_ic;
    }



    public double getAirAttack() {
        return air_attack;
    }

    public double getAirDefence() {
        return air_defence;
    }

    public double getAirRange() {
        return air_range;
    }

    public double getAirAgility() {
        return air_agility;
    }

    public double getAirGroundAttack() {
        return air_ground_attack;
    }

    public double getAirBombing() {
        return air_bombing;
    }

    public double getAirSuperiority() {
        return air_superiority;
    }

    public double getNavalStrikeAttack() {
        return naval_strike_attack;
    }

    public double getNavalStrikeTargetting() {
        return naval_strike_targetting;
    }

    public double getCarrierSize() {
        return carrier_size;
    }

    public double getDefaultCarrierCompositionWeight() {
        return default_carrier_composition_weight;
    }

    public boolean isCarrierCapable() {
        return carrier_capable;
    }
    public String[] getCanConvertFromModuleCategorie() {
        return can_convert_from_module_categorie;
    }
    public double[] getConvertCostIc() {
        return convert_cost_ic;
    }
}